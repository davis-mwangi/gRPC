package com.davidmwangi.server.metadata;


import com.davidmwangi.models.*;
import com.davidmwangi.server.rpctypes.AccountDatabase;
import com.davidmwangi.server.rpctypes.CashStreamingRequest;
import com.google.common.util.concurrent.Uninterruptibles;
import io.grpc.Context;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.protobuf.ProtoUtils;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.TimeUnit;

public class MetadataService extends BankServiceGrpc.BankServiceImplBase {

    @Override
    public void getBalance(BalanceCheckRequest request, StreamObserver<Balance> responseObserver) {
       int accountNumber =  request.getAccountNumber();
       int amount = AccountDatabase.getBalance(accountNumber);

       UserRole userRole =  ServerConstants.CTX_USER_ROLE.get();
       amount = UserRole.PRIME.equals(userRole) ?  amount : (amount - 15);

       System.out.println("User Role " + userRole);
       Balance balance = Balance.newBuilder()
               .setAmount(amount)
               .build();

       //Simulate time-consuming call
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);

       responseObserver.onNext(balance);
       responseObserver.onCompleted();
    }

    @Override
    public void withdraw(WithdrawRequest request, StreamObserver<Money> responseObserver) {
        int accountNumber=  request.getAccountNumber();
        int amount  =  request.getAmount();//10, 20, 30
        int balance  = AccountDatabase.getBalance(accountNumber);

        if(amount <  10 ||  (amount % 10) != 0){
            Metadata metadata =  new Metadata();
            Metadata.Key<WithdrawalError>errorKey = ProtoUtils.keyForProto(WithdrawalError.getDefaultInstance());
            WithdrawalError withdrawalError = WithdrawalError.newBuilder()
                    .setAmount(balance)
                    .setErrorMessage(ErrorMessage.ONLY_TEN_MULTIPLES)
                    .build();

            metadata.put(errorKey, withdrawalError);
            responseObserver.onError(Status.FAILED_PRECONDITION.asRuntimeException(metadata));

        }

        if(balance <  amount){
            Metadata metadata =  new Metadata();
            Metadata.Key<WithdrawalError>errorKey = ProtoUtils.keyForProto(WithdrawalError.getDefaultInstance());
            WithdrawalError withdrawalError = WithdrawalError.newBuilder()
                    .setAmount(balance)
                    .setErrorMessage(ErrorMessage.INSUFFICIENT_BALANCE)
                    .build();

            metadata.put(errorKey, withdrawalError);

//            Status status = Status.FAILED_PRECONDITION.withDescription("Insufficient Funds. Current balance" + balance);
            responseObserver.onError(Status.FAILED_PRECONDITION.asRuntimeException(metadata));
            return;
        }

        //All validations passed
        for (int i = 0; i < (amount /10); i++) {
            Money money = Money.newBuilder().setValue(10).build();

            //Simulate time-consuming call
            Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
            if(!Context.current().isCancelled()){
                responseObserver.onNext(money);
                AccountDatabase.deductBalance(accountNumber, 10);
                System.out.println("Delivered $10");
            }else{
                break;
            }

        }
        System.out.println("Completed!");
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<DepositRequest> cashDeposit(StreamObserver<Balance> responseObserver) {
        return new CashStreamingRequest(responseObserver);
    }

}

package com.davidmwangi.deadline;


import com.davidmwangi.models.*;
import com.davidmwangi.server.rpctypes.AccountDatabase;
import com.davidmwangi.server.rpctypes.CashStreamingRequest;
import com.google.common.util.concurrent.Uninterruptibles;
import io.grpc.Context;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.TimeUnit;

public class DeadlineService extends BankServiceGrpc.BankServiceImplBase {

    @Override
    public void getBalance(BalanceCheckRequest request, StreamObserver<Balance> responseObserver) {
       int accountNumber =  request.getAccountNumber();
       Balance balance = Balance.newBuilder()
               .setAmount(AccountDatabase.getBalance(accountNumber))
               .build();

       //Simulate time-consuming call
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);

       responseObserver.onNext(balance);
       responseObserver.onCompleted();
    }

    @Override
    public void withdraw(WithdrawRequest request, StreamObserver<Money> responseObserver) {
        int accountNumber=  request.getAccountNumber();
        int amount  =  request.getAmount();
        int balance  = AccountDatabase.getBalance(accountNumber);

        if(balance <  amount){
            Status status = Status.FAILED_PRECONDITION.withDescription("Insufficient Funds. Current balance" + balance);
            responseObserver.onError(status.asRuntimeException());
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

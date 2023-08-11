package com.davidmwangi.server;


import com.davidmwangi.models.Balance;
import com.davidmwangi.models.BalanceCheckRequest;
import com.davidmwangi.models.BankServiceGrpc;
import io.grpc.stub.StreamObserver;

public class BankService extends BankServiceGrpc.BankServiceImplBase {

    @Override
    public void getBalance(BalanceCheckRequest request, StreamObserver<Balance> responseObserver) {
       int accountNumber =  request.getAccountNumber();
       Balance balance = Balance.newBuilder()
               .setAmount(AccountDatabase.getBalance(accountNumber))
               .build();

       responseObserver.onNext(balance);
       responseObserver.onCompleted();
    }
}

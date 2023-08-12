package com.davidmwangi.server.rpctypes;

import com.davidmwangi.models.Balance;
import com.davidmwangi.models.DepositRequest;
import io.grpc.stub.StreamObserver;

public class CashStreamingRequest implements StreamObserver<DepositRequest> {

    private  final StreamObserver<Balance>balanceStreamObserver;
    private int accountBalance;

    public CashStreamingRequest(StreamObserver<Balance> balanceStreamObserver) {
        this.balanceStreamObserver = balanceStreamObserver;
    }

    @Override
    public void onNext(DepositRequest depositRequest) {
      int accountNumber = depositRequest.getAccountNumber();
      int amount = depositRequest.getAmount();
      AccountDatabase.addBalance(accountNumber, amount);
      this.accountBalance =  AccountDatabase.addBalance(accountNumber, amount);
    }

    @Override
    public void onError(Throwable throwable) {
        System.err.println("Error Processing  Cash Deposit");
    }

    @Override
    public void onCompleted() {
        Balance balance = Balance.newBuilder().setAmount(this.accountBalance).build();
        this.balanceStreamObserver.onNext(balance);
        this.balanceStreamObserver.onCompleted();
    }
}

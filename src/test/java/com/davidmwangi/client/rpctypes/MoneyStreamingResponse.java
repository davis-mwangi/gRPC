package com.davidmwangi.client.rpctypes;

import com.davidmwangi.client.metadata.ClientConstants;
import com.davidmwangi.models.Money;
import com.davidmwangi.models.WithdrawalError;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

public class MoneyStreamingResponse  implements StreamObserver<Money> {
    @Override
    public void onNext(Money money) {
        System.out.println("Received async :"+ money.getValue());
    }

    @Override
    public void onError(Throwable throwable) {
        Metadata metadata =  Status.trailersFromThrowable(throwable);
        WithdrawalError withdrawalError =  metadata.get(ClientConstants.WITHDRAWAL_ERROR_KEY);
        System.out.println(withdrawalError.getAmount() +" : " + withdrawalError.getErrorMessage());

        System.out.println(throwable.getMessage());
    }

    @Override
    public void onCompleted() {
        System.out.println("Server Done!");
    }
}

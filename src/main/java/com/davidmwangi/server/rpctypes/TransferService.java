package com.davidmwangi.server.rpctypes;

import com.davidmwangi.models.TransferRequest;
import com.davidmwangi.models.TransferResponse;
import com.davidmwangi.models.TransferServiceGrpc;
import io.grpc.stub.StreamObserver;

public class TransferService  extends TransferServiceGrpc.TransferServiceImplBase {
    @Override
    public StreamObserver<TransferRequest> transfer(StreamObserver<TransferResponse> responseObserver) {
        return  new TransferStreamingRequest(responseObserver);
    }
}

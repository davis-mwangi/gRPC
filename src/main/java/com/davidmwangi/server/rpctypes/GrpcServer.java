package com.davidmwangi.server.rpctypes;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class GrpcServer {
    public static void main(String[] args) {
        Server server = ServerBuilder.forPort(6565)
                .addService(new BankService())
                .addService(new TransferService())
                .build();
        try {
            server.start();
            server.awaitTermination();
        } catch (Exception e ) {
            throw new RuntimeException(e);
        }
    }
}

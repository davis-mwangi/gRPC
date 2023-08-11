package com.davidmwangi.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GrpcServer {
    public static void main(String[] args) {
        Server server = ServerBuilder.forPort(6565)
                .addService(new BankService())
                .build();
        try {
            server.start();
            server.awaitTermination();
        } catch (Exception e ) {
            throw new RuntimeException(e);
        }
    }
}

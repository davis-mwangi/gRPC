package com.davidmwangi.server.loadbalancing;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class GrpcServer2 {
    public static void main(String[] args) {
        Server server = ServerBuilder.forPort(7575)
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

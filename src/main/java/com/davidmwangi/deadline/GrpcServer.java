package com.davidmwangi.deadline;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class GrpcServer {
    public static void main(String[] args) {
        Server server = ServerBuilder.forPort(6565)
                .addService(new DeadlineService())
                .build();
        try {
            server.start();
            server.awaitTermination();
        } catch (Exception e ) {
            throw new RuntimeException(e);
        }
    }
}

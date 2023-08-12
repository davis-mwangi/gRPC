package com.davidmwangi.server.metadata;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class GrpcServer {
    public static void main(String[] args) {
        Server server = ServerBuilder.forPort(6565)
//                .intercept(new AuthInterceptor())
                .addService(new MetadataService())
                .build();
        try {
            server.start();
            server.awaitTermination();
        } catch (Exception e ) {
            throw new RuntimeException(e);
        }
    }
}

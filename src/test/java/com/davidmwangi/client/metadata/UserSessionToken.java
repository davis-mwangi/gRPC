package com.davidmwangi.client.metadata;

import io.grpc.CallCredentials;
import io.grpc.Metadata;
import io.grpc.Status;

import java.util.concurrent.Executor;

public class UserSessionToken extends CallCredentials {
    private String jwt;

    public UserSessionToken(String jwt){
        this.jwt = jwt;
    }
    @Override
    public void applyRequestMetadata(RequestInfo requestInfo, Executor executor, MetadataApplier metadataApplier) {
       executor.execute(() -> {
           Metadata metadata = new Metadata();
           metadata.put(ClientConstants.USER_TOKEN, this.jwt);
           metadataApplier.apply(metadata);

           //Handle failure
//           metadataApplier.fail(Status.ABORTED);
       });

    }

    @Override
    public void thisUsesUnstableApi() {
        //may change in future
    }
}

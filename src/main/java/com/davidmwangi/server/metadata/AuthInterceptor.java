package com.davidmwangi.server.metadata;

import io.grpc.*;

import java.util.Objects;
/*
user-secret-3:prime
user-secret-2:regular

 */
public class AuthInterceptor implements ServerInterceptor {
    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> serverCall, Metadata metadata, ServerCallHandler<ReqT, RespT> serverCallHandler) {
       String clientToken =  metadata.get(ServerConstants.USER_TOKEN);
       if(this.validate(clientToken)){
           UserRole userRole= this.extractUserRole(clientToken);
           Context context = Context.current().withValue(ServerConstants.CTX_USER_ROLE, userRole);
           return Contexts.interceptCall(context, serverCall,metadata, serverCallHandler);
//           return serverCallHandler.startCall(serverCall, metadata);
       }else{
           Status status = Status.UNAUTHENTICATED.withDescription("Invalid Token");
           serverCall.close(status, metadata);
       }
        return new ServerCall.Listener<ReqT>() {
            @Override
            public void onMessage(ReqT message) {
                super.onMessage(message);
            }
        };
    }
    private boolean validate(String token){
        return Objects.nonNull(token) && (token.startsWith("user-secret-3") ||  token.startsWith("user-secret-2"));
    }
    private UserRole extractUserRole(String jwt){
        return jwt.endsWith("prime") ? UserRole.PRIME : UserRole.STANDARD;
    }
}

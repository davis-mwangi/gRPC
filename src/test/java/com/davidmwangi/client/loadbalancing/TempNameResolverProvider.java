package com.davidmwangi.client.loadbalancing;

import io.grpc.NameResolver;
import io.grpc.NameResolverProvider;

import java.net.SocketAddress;
import java.net.URI;
import java.util.Collection;

public class TempNameResolverProvider extends NameResolverProvider {
    public TempNameResolverProvider() {
        super();
    }

    @Override
    public NameResolver newNameResolver(URI targetUri, NameResolver.Args args) {
        System.out.println("Looking for service: " +  targetUri.toString());
        return new TempNameResolver(targetUri.toString());
    }

    @Override
    protected String getScheme() {
        return super.getScheme();
    }

    @Override
    public String getDefaultScheme() {
        return "dns";
    }

    @Override
    protected Collection<Class<? extends SocketAddress>> getProducedSocketAddressTypes() {
        return super.getProducedSocketAddressTypes();
    }

    @Override
    protected boolean isAvailable() {
        return true;
    }

    @Override
    protected int priority() {
        return 5;
    }
}

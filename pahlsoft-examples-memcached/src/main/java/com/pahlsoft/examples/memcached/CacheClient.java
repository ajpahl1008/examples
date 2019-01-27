package com.pahlsoft.examples.memcached;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.ConnectionFactoryBuilder;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.auth.AuthDescriptor;
import net.spy.memcached.auth.PlainCallbackHandler;

import java.io.IOException;

public class CacheClient {
       static  MemcachedClient memcachedClient;
    public static void main(String[] args) throws InterruptedException {
        AuthDescriptor authDescriptor = new AuthDescriptor(new String[]{"PLAIN"},
                new PlainCallbackHandler("cacheuser", "password"))
        );

        try {
            if (memcachedClient == null) {
                memcachedClient = new MemcachedClient(

                        new ConnectionFactoryBuilder().setProtocol(ConnectionFactoryBuilder.Protocol.BINARY)
                                .setAuthDescriptor(authDescriptor)
                                .build(),
                        AddrUtil.getAddresses("192.168.0.201:11211 192.168.0.201:11211"));
            }
        } catch (IOException ex) {
            System.err.println("Couldn't create a connection, bailing out: \nIOException " + ex.getMessage());
        }

//        Thread.sleep(2000);

        System.out.println("publishing... firstkey");
        memcachedClient.append("firstkey", new String("first_dude"));

        Thread.sleep(2000);

        System.out.println("publishing... second");
        memcachedClient.append("secondkey", new String("second_dude"));

        memcachedClient.flush();

        Thread.sleep(2000);

        Object response =  memcachedClient.get("secondkey");


//        System.out.println("SecondKey: " + response);

        Object response2 = memcachedClient.get("firstkey");

//        System.out.println("FirstKey: " + response);

        System.exit(0);

    }
}

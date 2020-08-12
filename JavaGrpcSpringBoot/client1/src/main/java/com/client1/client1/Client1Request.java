package com.client1.client1;

import com.grpcservice.proto.lib.*;


import org.springframework.stereotype.Service;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;


@Service
public class Client1Request {

    
    
    public String receiveGreeting(String name) {

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();

        MyServiceGrpc.MyServiceBlockingStub stub
                = MyServiceGrpc.newBlockingStub(channel);

        
        HelloRequest request = HelloRequest.newBuilder()
                .setName(name)
                .build();


        return stub.sayHello(request).getMessage();
    }
}
package com.github.antezovko23;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
// import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import com.grpcLib.generatedStubs.*;
import com.grpcLib.generatedStubs.Number;


@Service
public class ClientRequest {

    
    

    public int sendIncrementRequest(int num){

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
        .usePlaintext()
        .build();


        AdditionGrpc.AdditionBlockingStub stub 
        = AdditionGrpc.newBlockingStub(channel);

        Number request = Number.newBuilder()
        .setNum(num)
        .build();

        return stub.increment(request).getNum();



    }

    

    
}
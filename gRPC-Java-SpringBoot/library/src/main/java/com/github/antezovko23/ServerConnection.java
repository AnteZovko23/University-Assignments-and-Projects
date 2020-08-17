package com.github.antezovko23;
// package com.client.client1;

import com.grpcLib.generatedStubs.AdditionGrpc;
import com.grpcLib.generatedStubs.Request;

// import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;


/**
 * The client request creates a channel with the
 * server and sends the data to be processed
 * 
 * 
 * 
 */
@Service
public class ServerConnection {



    // Creates the channel to connect to the server
    ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
        .usePlaintext()
        .build();


        AdditionGrpc.AdditionBlockingStub stub 
        = AdditionGrpc.newBlockingStub(channel);


    /**
     * sendIncrementRequest sends a number to be incremented by
     * the Server
     * 
     * 
     * Parameter: num - The number to be incremented
     * 
     * Returns: The incremented number
     */
    public int sendIncrementRequest(int num, int step){


        Request request = Request.newBuilder()
        .setNum(num)
        .setStep(step)
        .build();

        return stub.increment(request).getReplyNum();



    }

    

    
}
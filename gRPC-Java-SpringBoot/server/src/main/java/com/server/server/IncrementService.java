package com.server.server;

import com.grpcLib.generatedStubs.AdditionGrpc.AdditionImplBase;
import com.grpcLib.generatedStubs.Reply;
import com.grpcLib.generatedStubs.Request;


import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;




/**
 * gRPC service that increments a given number and and returns it through 
 * the gRPC channel
 * 
 * gRPCService tag makes the method visible to the client
 * 
 * 
 */
@GrpcService
public class IncrementService extends AdditionImplBase  {

    /**
     * 
     * Increment method
     * 
     * AdditionImplBase, Increment and Number are Classes generated in the library
     * using Protocol Buffers
     * 
     * Parameters: Request request - The number sent by the client and the step
     * 
     * Returns: Reply - Incremented number
     * 
     */
    @Override
    public void increment(Request request, StreamObserver<Reply> responseObserver){
        // Get the number
        int incrementedNum = request.getNum() + request.getStep();


        // Build the reply
        Reply reply = Reply.newBuilder()
                                    .setReplyNum(incrementedNum)
                                    .build();

        

        // Send the reply
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
    
}
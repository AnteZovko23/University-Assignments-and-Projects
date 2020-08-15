package com.server.server;

import com.grpcLib.generatedStubs.AdditionGrpc.AdditionImplBase;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import com.grpcLib.generatedStubs.Number;

@GrpcService
public class IncrementService extends AdditionImplBase  {

    @Override
    public void increment(Number num, StreamObserver<Number> responseObserver){
        int incrementedNum = num.getNum() + 1;
        Number reply = Number.newBuilder()
                                    .setNum(incrementedNum)
                                    .build();

        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
    
}
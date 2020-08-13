package com.grpcserver.server;

import net.devh.boot.grpc.server.service.GrpcService;

import com.grpcservice.proto.lib.HelloReply;
import com.grpcservice.proto.lib.HelloRequest;
import com.grpcservice.proto.lib.MyServiceGrpc.MyServiceImplBase;

import io.grpc.stub.StreamObserver;

@GrpcService
public class Server extends MyServiceImplBase {
    
    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        HelloReply reply = HelloReply.newBuilder()
                .setMessage("Hello, August 12: " + request.getName())
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }


}
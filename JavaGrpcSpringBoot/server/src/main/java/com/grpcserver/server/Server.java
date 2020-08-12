package com.grpcserver.server;

import com.grpcservice.proto.lib.*;
import com.grpcservice.proto.lib.MyServiceGrpc.MyServiceImplBase;

import net.devh.boot.grpc.server.service.GrpcService;
import io.grpc.stub.StreamObserver;

@GrpcService
public class Server extends MyServiceImplBase {
    

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        HelloReply reply = HelloReply.newBuilder()
                .setMessage("Hello, al ovo radi stvarno: " + request.getName())
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }


}
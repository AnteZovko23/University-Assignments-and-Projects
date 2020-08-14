package com.grpcLib.generatedStubs;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.31.0)",
    comments = "Source: Service.proto")
public final class AdditionGrpc {

  private AdditionGrpc() {}

  public static final String SERVICE_NAME = "com.protolib.Addition";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.grpcLib.generatedStubs.Number,
      com.grpcLib.generatedStubs.Number> getIncrementMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Increment",
      requestType = com.grpcLib.generatedStubs.Number.class,
      responseType = com.grpcLib.generatedStubs.Number.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.grpcLib.generatedStubs.Number,
      com.grpcLib.generatedStubs.Number> getIncrementMethod() {
    io.grpc.MethodDescriptor<com.grpcLib.generatedStubs.Number, com.grpcLib.generatedStubs.Number> getIncrementMethod;
    if ((getIncrementMethod = AdditionGrpc.getIncrementMethod) == null) {
      synchronized (AdditionGrpc.class) {
        if ((getIncrementMethod = AdditionGrpc.getIncrementMethod) == null) {
          AdditionGrpc.getIncrementMethod = getIncrementMethod =
              io.grpc.MethodDescriptor.<com.grpcLib.generatedStubs.Number, com.grpcLib.generatedStubs.Number>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Increment"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpcLib.generatedStubs.Number.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpcLib.generatedStubs.Number.getDefaultInstance()))
              .setSchemaDescriptor(new AdditionMethodDescriptorSupplier("Increment"))
              .build();
        }
      }
    }
    return getIncrementMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AdditionStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AdditionStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AdditionStub>() {
        @java.lang.Override
        public AdditionStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AdditionStub(channel, callOptions);
        }
      };
    return AdditionStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AdditionBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AdditionBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AdditionBlockingStub>() {
        @java.lang.Override
        public AdditionBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AdditionBlockingStub(channel, callOptions);
        }
      };
    return AdditionBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AdditionFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AdditionFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AdditionFutureStub>() {
        @java.lang.Override
        public AdditionFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AdditionFutureStub(channel, callOptions);
        }
      };
    return AdditionFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class AdditionImplBase implements io.grpc.BindableService {

    /**
     */
    public void increment(com.grpcLib.generatedStubs.Number request,
        io.grpc.stub.StreamObserver<com.grpcLib.generatedStubs.Number> responseObserver) {
      asyncUnimplementedUnaryCall(getIncrementMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getIncrementMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.grpcLib.generatedStubs.Number,
                com.grpcLib.generatedStubs.Number>(
                  this, METHODID_INCREMENT)))
          .build();
    }
  }

  /**
   */
  public static final class AdditionStub extends io.grpc.stub.AbstractAsyncStub<AdditionStub> {
    private AdditionStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AdditionStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AdditionStub(channel, callOptions);
    }

    /**
     */
    public void increment(com.grpcLib.generatedStubs.Number request,
        io.grpc.stub.StreamObserver<com.grpcLib.generatedStubs.Number> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getIncrementMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class AdditionBlockingStub extends io.grpc.stub.AbstractBlockingStub<AdditionBlockingStub> {
    private AdditionBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AdditionBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AdditionBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.grpcLib.generatedStubs.Number increment(com.grpcLib.generatedStubs.Number request) {
      return blockingUnaryCall(
          getChannel(), getIncrementMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class AdditionFutureStub extends io.grpc.stub.AbstractFutureStub<AdditionFutureStub> {
    private AdditionFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AdditionFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AdditionFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.grpcLib.generatedStubs.Number> increment(
        com.grpcLib.generatedStubs.Number request) {
      return futureUnaryCall(
          getChannel().newCall(getIncrementMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_INCREMENT = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AdditionImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(AdditionImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_INCREMENT:
          serviceImpl.increment((com.grpcLib.generatedStubs.Number) request,
              (io.grpc.stub.StreamObserver<com.grpcLib.generatedStubs.Number>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class AdditionBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AdditionBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.grpcLib.generatedStubs.Service.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Addition");
    }
  }

  private static final class AdditionFileDescriptorSupplier
      extends AdditionBaseDescriptorSupplier {
    AdditionFileDescriptorSupplier() {}
  }

  private static final class AdditionMethodDescriptorSupplier
      extends AdditionBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    AdditionMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (AdditionGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AdditionFileDescriptorSupplier())
              .addMethod(getIncrementMethod())
              .build();
        }
      }
    }
    return result;
  }
}

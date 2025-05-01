package cn.andychen.helloworld.server.service;


import cn.andychen.grpc.HelloReply;
import cn.andychen.grpc.HelloRequest;
import cn.andychen.grpc.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

/**
 * @Author Chen Zhenyang
 * @Date 2025/5/1 15:39
 * @Description
 */
@GrpcService
public class HelloGrpcService extends HelloServiceGrpc.HelloServiceImplBase {
    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        String message = "Hello from server, " + request.getName();
        HelloReply reply = HelloReply.newBuilder().setMessage(message).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
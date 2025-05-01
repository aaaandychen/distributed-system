package cn.andychen.helloworld;

import cn.andychen.grpc.HelloReply;
import cn.andychen.grpc.HelloRequest;
import cn.andychen.grpc.HelloServiceGrpc;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

/**
 * @Author Chen Zhenyang
 * @Date 2025/5/1 12:24
 * @Description
 */
public class HelloServer {

    public static void main(String[] args) throws Exception {
        Server server = ServerBuilder.forPort(5005)
                .addService(new HelloServiceImpl())
                .build()
                .start();

        System.out.println("Server started on port 50051");
        server.awaitTermination();
    }

    static class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {
        public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
            String message = "Hello, " + req.getName();
            HelloReply reply = HelloReply.newBuilder().setMessage(message).build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }
    }
}

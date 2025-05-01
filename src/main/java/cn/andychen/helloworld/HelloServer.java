package cn.andychen.helloworld;

import helloworld.HelloProto;
import helloworld.HelloServiceGrpc;
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
        Server server = ServerBuilder.forPort(50051)
                .addService(new HelloServiceImpl())
                .build()
                .start();

        System.out.println("Server started on port 50051");
        server.awaitTermination();
    }

    static class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {
        public void sayHello(HelloProto.HelloRequest req, StreamObserver<HelloProto.HelloReply> responseObserver) {
            String message = "Hello, " + req.getName();
            HelloProto.HelloReply reply = HelloProto.HelloReply.newBuilder().setMessage(message).build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }
    }
}

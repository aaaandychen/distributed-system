package cn.andychen.helloworld;

import helloworld.HelloProto;
import helloworld.HelloServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * @Author Chen Zhenyang
 * @Date 2025/5/1 12:24
 * @Description
 */
public class HelloClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        HelloServiceGrpc.HelloServiceBlockingStub stub = HelloServiceGrpc.newBlockingStub(channel);

        HelloProto.HelloReply response = stub.sayHello(HelloProto.HelloRequest.newBuilder().setName("Changyi").build());
        System.out.println("Received: " + response.getMessage());

        channel.shutdown();
    }
}

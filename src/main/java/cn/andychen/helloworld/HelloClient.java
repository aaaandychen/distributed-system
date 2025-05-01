package cn.andychen.helloworld;


import cn.andychen.grpc.HelloReply;
import cn.andychen.grpc.HelloRequest;
import cn.andychen.grpc.HelloServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * @Author Chen Zhenyang
 * @Date 2025/5/1 12:24
 * @Description
 */
public class HelloClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 5005)
                .usePlaintext()
                .build();

        HelloServiceGrpc.HelloServiceBlockingStub stub = HelloServiceGrpc.newBlockingStub(channel);

        HelloReply response = stub.sayHello(HelloRequest.newBuilder().setName("Zhenyang").build());
        System.out.println("Received: " + response.getMessage());

        channel.shutdown();
    }
}

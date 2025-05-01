package cn.andychen.helloworld.client.service;


import cn.andychen.grpc.HelloReply;
import cn.andychen.grpc.HelloRequest;
import cn.andychen.grpc.HelloServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Author Chen Zhenyang
 * @Date 2025/5/1 15:34
 * @Description
 */

@Component
public class HelloGrpcRunner implements CommandLineRunner {

    @GrpcClient("hello-server")
    private HelloServiceGrpc.HelloServiceBlockingStub stub;


    @Override
    public void run(String... args) {
        HelloRequest request = HelloRequest.newBuilder().setName("Joy").build();
        HelloReply reply = stub.sayHello(request);
        System.out.println("Received from server: " + reply.getMessage());
    }
}
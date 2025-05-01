package cn.andychen.mr.rpc;

import cn.andychen.grpc.HelloReply;
import cn.andychen.grpc.HelloRequest;
import cn.andychen.grpc.HelloServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * @Author Chen Zhenyang
 * @Date 2025/5/1 22:13
 * @Description
 */
public class MasterClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        MasterServiceGrpc.MasterServiceBlockingStub stub = MasterServiceGrpc.newBlockingStub(channel);

        Mr.MasterResponse response = stub.dispatchTask(Mr.MasterRequest.newBuilder().build());

        System.out.println("吴心愉女士");
        channel.shutdown();
    }
}

package cn.andychen.mr.rpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * @Author Chen Zhenyang
 * @Date 2025/5/1 22:12
 * @Description
 */
public class WorkerClient {

    ManagedChannel channel;
    WorkerServiceGrpc.WorkerServiceBlockingStub stub;
    public WorkerClient() {
        this.channel = ManagedChannelBuilder.forAddress("localhost", 50052)
                .usePlaintext()
                .build();
        this.stub = WorkerServiceGrpc.newBlockingStub(channel);
    }
}

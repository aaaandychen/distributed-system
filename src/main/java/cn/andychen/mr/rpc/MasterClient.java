package cn.andychen.mr.rpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * @Author Chen Zhenyang
 * @Date 2025/5/1 22:13
 * @Description 主节点客户端，由工作进程发起
 */
public class MasterClient {
    private final ManagedChannel channel;
    private final MasterServiceGrpc.MasterServiceBlockingStub stub; // 后面都基于存根做操作

    public MasterClient() {
        this.channel = ManagedChannelBuilder.forAddress("localhost", 50052)
                .usePlaintext()
                .build();
        this.stub = MasterServiceGrpc.newBlockingStub(channel);
    }

    public void ending(){
        this.channel.shutdown();
    }

    public void demo(){
        Mr.MasterRequest request = Mr.MasterRequest.newBuilder()
                .setSource("127.0.0.1:3306")
                .build();
        Mr.MasterResponse response = stub.dispatchTask(request);
        System.out.println(response);
    }
    public static void main(String[] args) {
        MasterClient masterClient = new MasterClient();
        masterClient.demo();
        masterClient.ending();
    }
}

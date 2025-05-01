package cn.andychen.mr.rpc;

import cn.andychen.grpc.HelloReply;
import cn.andychen.helloworld.HelloServer;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;

/**
 * @Author Chen Zhenyang
 * @Date 2025/5/1 22:12
 * @Description
 */
public class MasterServer {
    public static void main(String[] args) throws IOException, InterruptedException {

        Server server = ServerBuilder.forPort(50051)
                .addService(new MasterServerImpl())
                .build()
                .start();

        System.out.println("Server started on port 50051");
        server.awaitTermination();
    }

    static class MasterServerImpl extends MasterServiceGrpc.MasterServiceImplBase{
        public void dispatchTask(Mr.MasterRequest req, StreamObserver<Mr.MasterResponse> streamObserver){
            System.out.println("收到了");
            Mr.MasterResponse reply = Mr.MasterResponse.newBuilder().build();
            streamObserver.onNext(reply);
            streamObserver.onCompleted();
        }
    }
}

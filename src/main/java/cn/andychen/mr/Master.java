package cn.andychen.mr;

import cn.andychen.mr.rpc.WorkerClient;
import cn.andychen.mr.rpc.WorkerServiceGrpc;
import io.grpc.ManagedChannel;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author Chen Zhenyang
 * @Date 2025/4/28 21:17
 * @Description 主节点
 */
public class Master {
    private final ReentrantLock taskLock = new ReentrantLock(); // 分配任务需要互斥
    private final MasterHandler handler = new MasterHandler(); // 提供给server操作主节点变量

    static class Task {
        Long taskId;
        List<Worker> workers;
        Integer taskType;
    }

    static class Worker {
        Long workerId;
        WorkerClient workerClient;
        Integer state;
        Task task;
    }


    public static void main(String[] args) throws InterruptedException {
        StartMRMaster(args);
    }

    /*
    // 启动一个Master节点
    1. 维护工作线程列表，并需要定时任务发心跳包
        1.1 若失败，则需要通知所有其他工作线程，将任务转移到新的工作线程
        1.2 所有工作线程的reduce工作需要根据已经读取的文件判断是否重做
    2. 监听端口，接收rpc请求
        2.1 新接入的worker注册
        2.2 如果有，接收工作完成信息并提供新工作
        2.3 如果有性能差的节点，则将任务分配给别的进程，如果其中之一完成则通知其他进程
     */
    public static void StartMRMaster(String[] args) throws InterruptedException {
        if (args.length < 1) {
            System.out.println("error");
            return;
        }

        Master master = Master.MakeMaster(args, 10);
        master.server();
        while (!master.Done()) Thread.sleep(1000);
        System.out.println("ok!");
    }

    private void server() {
        // 在这里接入rpc，监听工作进程请求
    }

    public Boolean Done() {
        // todo:这里需要阶段性地查看是否完成job，其实就是我们需要的定时任务
        System.out.println("checking if is done...");
        return true;
    }

    // 由程序入口调用，nReduce是Reduce线程的数量
    public static Master MakeMaster(String[] files, int nReduce) {
        Master master = new Master();
        // todo：初始化

        master.server();
        return master;
    }

    class MasterHandler{

    }
}

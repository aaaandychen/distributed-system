package cn.andychen.mr;

import cn.andychen.mrapps.IMethod;
import cn.andychen.mrapps.impl.Indexer;
import cn.andychen.mrapps.impl.WordCounter;

import static cn.andychen.Constants.AppType.AppType_INDEXER;
import static cn.andychen.Constants.AppType.AppType_WC;

/**
 * @Author Chen Zhenyang
 * @Date 2025/4/28 21:18
 * @Description 工作节点
 */
public class Worker {
    private Integer id;
    private IMethod method;
    private int ReduceNum;

    public static void main(String[] args) {
        StartMRWorker(args);
    }

    public static void StartMRWorker(String[] args) {
        Worker worker = new Worker(args[0], Integer.parseInt(args[1]));
        worker.work();
    }

    public Worker(String method, int ReduceNum) {
        this.method = loadPlugin(method);
        this.ReduceNum = ReduceNum;
    }

    // hash来指向对应reduce
    private int ihash(String key) {
        return key.hashCode() & 0x7fffffff;
    }

    // 主进程启动worker
    public void work() {
        /*
        循环执行，请求master分配任务
        执行任务
        master广播-》中断任务->FutureTask
         */
    }

    // 提供外部调用，通过rpc访问Master
    public boolean callMaster(Object args, Object response, String rpcName) {
        return false;
    }

    // 具体调用逻辑
    private void doCall() {
    }

    /*
    自定义mrapp流程：在mrapps.Constants.AppType中新增类型，然后在mrapps/impl中新增一个IWorker的实现类
     */
    public static IMethod loadPlugin(String methodType) {
        switch (methodType) {
            case AppType_WC:
                return new WordCounter();
            case AppType_INDEXER:
                return new Indexer();
            default:
                return null;
        }

    }
}

package cn.andychen.mr;

/**
 * @Author Chen Zhenyang
 * @Date 2025/4/28 21:17
 * @Description 主节点
 */
public class Master {

    // 示例启动一个Master节点
    public static void main(String[] args) throws InterruptedException {
        if(args.length < 1){
            System.out.println("error");
            return;
        }

        Master master = Master.MakeMaster(args,10);
        master.server();
        while (!master.Done())Thread.sleep(1000);
        System.out.println("ok!");
    }

    private void server(){
        // 在这里接入rpc，监听工作进程请求
    }

    public Boolean Done(){
        // todo:这里需要阶段性地查看是否完成job
        return true;
    }

    // 由程序入口调用，nReduce是Reduce线程的数量
    public static Master MakeMaster(String[] files, int nReduce){
        Master master = new Master();
        // todo

        master.server();
        return master;
    }
}

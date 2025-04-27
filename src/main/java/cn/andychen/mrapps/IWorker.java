package cn.andychen.mrapps;

import cn.andychen.mr.KeyValue;

/**
 * @Author Chen Zhenyang
 * @Date 2025/4/27 21:52
 * @Description Worker线程执行的Map和Reduce方法
 */
public interface IWorker {
    public KeyValue[] map(String filename,String content);
    public String reduce(String key,String[] values);
}

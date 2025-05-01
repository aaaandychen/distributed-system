package cn.andychen.mrapps.impl;

import cn.andychen.mr.KeyValue;
import cn.andychen.mrapps.IMethod;

/**
 * @Author Chen Zhenyang
 * @Date 2025/4/27 22:12
 * @Description
 */
public class Indexer implements IMethod {
    @Override
    public KeyValue[] map(String filename, String content) {
        return new KeyValue[0];
    }

    @Override
    public String reduce(String key, String[] values) {
        return null;
    }
}

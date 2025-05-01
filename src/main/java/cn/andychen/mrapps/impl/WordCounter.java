package cn.andychen.mrapps.impl;

import cn.andychen.mr.KeyValue;
import cn.andychen.mrapps.IMethod;

import java.util.ArrayList;

/**
 * @Author Chen Zhenyang
 * @Date 2025/4/27 22:10
 * @Description
 */
public class WordCounter implements IMethod {
    @Override
    public KeyValue[] map(String filename, String content) {
        String[] splits = content.split("[^a-zA-Z]+");
        ArrayList<KeyValue> res = new ArrayList<>();
        for (String i : splits) {
            if (i.isEmpty()) continue;
            res.add(new KeyValue(i, ""));
        }
        return res.toArray(new KeyValue[0]);
    }

    @Override
    public String reduce(String key, String[] values) {
        return String.valueOf(values.length);
    }
}

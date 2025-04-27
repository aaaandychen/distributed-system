package cn.andychen.mr;

/**
 * @Author Chen Zhenyang
 * @Date 2025/4/27 21:54
 * @Description mapreduce的键值类，存储map产生的中间结果
 */
public class KeyValue {
    public String key;
    public String value;

    public KeyValue(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public KeyValue() {
    }
}

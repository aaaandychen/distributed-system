package cn.andychen;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @Author Chen Zhenyang
 * @Date 2025/4/27 22:08
 * @Description mapreduce类型
 */
public class Constants {
    public static final String ROOT_PATH = "D:/Projects/distributed-system/src/main/java/cn/andychen/";
    // 文件读取绝对路径，方便脚本调用
    public static final String DATA_PATH = "D:/Projects/distributed-system/data/";
    public static final String SEP = " ";
    public static final String EOL = "\n";
    // mapReduce具体任务类别
    public class AppType{
        public static final String AppType_WC = "word_counter";
        public static final String AppType_INDEXER = "indexer";
    }
}

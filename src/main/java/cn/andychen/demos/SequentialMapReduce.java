package cn.andychen.demos;

import cn.andychen.mr.KeyValue;
import cn.andychen.mrapps.IWorker;
import cn.andychen.mrapps.impl.Indexer;
import cn.andychen.mrapps.impl.WordCounter;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static cn.andychen.Constants.*;
import static cn.andychen.Constants.AppType.AppType_INDEXER;
import static cn.andychen.Constants.AppType.AppType_WC;
import static cn.andychen.mr.Worker.loadPlugin;
import static java.nio.file.StandardOpenOption.APPEND;

/**
 * @Author Chen Zhenyang
 * @Date 2025/4/27 21:59
 * @Description 顺序型mapreduce例子
 */
public class SequentialMapReduce {

    /*
    参数传入方法，即mrapps中实现类，做一个switch来枚举
     */
    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            throw new Exception("输入参数错误");
        }

        IWorker worker = loadPlugin(args[0]);
        if (worker == null) {
            System.out.println("不存在该worker！");
            return;
        }

        ArrayList<KeyValue> intermediate = new ArrayList<>();
        for (int i = 1; i < args.length; i++) {
            String filename = args[i];
            byte[] bytes = Files.readAllBytes(Paths.get(DATA_PATH + filename));
            String content = new String(bytes);
            KeyValue[] kvList = worker.map(filename, content);
            Collections.addAll(intermediate, kvList);
        }
        intermediate.sort(new Comparator<KeyValue>() {
            @Override
            public int compare(KeyValue o1, KeyValue o2) {
                return o1.key.compareTo(o2.key);
            }
        });

        Path filePath = Paths.get("mr-out-0");

        try {
            // 1. 如果文件存在则删除
            if (Files.exists(filePath)) {
                Files.delete(filePath);
            }

            // 2. 创建新的空文件
            Files.createFile(filePath);

            // 3. 写入内容
            for (int i = 0; i < intermediate.size(); i++) {
                int j = i;
                while (j < intermediate.size() && intermediate.get(j).key.equals(intermediate.get(i).key)) {
                    j++;
                }

                ArrayList<String> collector = new ArrayList<>();
                for (int k = i; k < j; k++) {
                    collector.add(intermediate.get(k).value);
                }

                String output = worker.reduce(intermediate.get(i).key, collector.toArray(new String[0]));

                Files.write(filePath, (intermediate.get(i).key + SEP + output + EOL).getBytes(StandardCharsets.UTF_8), APPEND);

                i = j;
            }

        } catch (IOException e) {
            System.err.println("文件操作出错: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("处理结束...");
    }

}

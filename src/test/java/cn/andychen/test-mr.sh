#!/bin/sh

# 基础的mr测试
# Windows 默认的命令行环境（如 cmd 或 PowerShell）不支持直接运行 sh 脚本，可以用git bash

# 在空文件夹中运行测试
rm -rf mr-tmp
mkdir mr-tmp || exit 1
cd mr-tmp || exit 1
rm -f mr-*

# 编译文件
javac -encoding UTF-8 -d . $(find ../../../../..//main/java/cn/andychen -name "*.java")


#failed_any=0

# 第一个测试word counter

# 基于线性MapReduce生成正确输出
java -Dfile.encoding=UTF-8 -cp . cn.andychen.demos.SequentialMapReduce word_counter pg-tom_sawyer.txt
sort mr-out-0 > mr-correct-wc.txt
rm -f mr-out*

echo '***' Starting wc test.

timeout -k 2s 180s java -Dfile.encoding=UTF-8 -cp . cn.andychen.mr.Master pg-tom_sawyer.txt &

# 给master时间准备资源
sleep 1

## start multiple workers.
timeout -k 2s 180s java -Dfile.encoding=UTF-8 -cp . cn.andychen.mr.Worker word_counter &
timeout -k 2s 180s java -Dfile.encoding=UTF-8 -cp . cn.andychen.mr.Worker word_counter &
timeout -k 2s 180s java -Dfile.encoding=UTF-8 -cp . cn.andychen.mr.Worker word_counter &

# wait for one of the processes to exit.
# under bash, this waits for all processes,
# including the master.
wait

# the master or a worker has exited. since workers are required
# to exit when a job is completely finished, and not before,
# that means the job has finished.

sort mr-out* | grep . > mr-wc-all
if cmp mr-wc-all mr-correct-wc.txt
then
  echo '---' wc test: PASS
else
  echo '---' wc output is not the same as mr-correct-wc.txt
  echo '---' wc test: FAIL
  failed_any=1
fi

# wait for remaining workers and master to exit.
wait ; wait ; wait


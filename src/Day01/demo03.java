package Day01;

import java.util.UUID;

/**
 * Stringtable 性能调优实验
 * 用于配合jcmd 观察不同 -xx:+UseStringTable 运行结果
 */
public class demo03 {
    public static void main(String[] args) {
        System.out.println("===============demo03 start===============");

        //可调节参数：可以往stringtable里面插多少条数据
        int count = 1000000;

        System.out.println("准备插入" + count + "条数据");

        long start = System.currentTimeMillis();
        for(int i = 0; i < count; i++){
            String uuid = UUID.randomUUID().toString().substring(0, 8);
//            uuid.intern();
        }
        long end = System.currentTimeMillis();
        System.out.println("插入" + count + "条数据耗时: " + (end - start) + "ms");

        System.out.println("\n========================================");
        System.out.println("进程已启动，请执行以下命令查看 StringTable 状态：");
        System.out.println("  1. jps -l          (找到本进程的 PID)");
        System.out.println("  2. jcmd <PID> VM.stringtable  (查看统计信息)");
        System.out.println("按 Enter 键结束程序...");
        System.out.println("========================================");

        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("===============demo03 end===============");
    }
}

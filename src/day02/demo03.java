package day02;

import java.nio.ByteBuffer;

public class demo03 {
    public static void main(String[] args) throws Exception {
        System.out.println("请打开任务管理器，找到 java.exe，记下当前内存");
        System.out.println("按回车键开始分配 1GB 直接内存...");
        System.in.read();

        ByteBuffer buffer = ByteBuffer.allocateDirect(1024 * 1024 * 1024);
        System.out.println("分配完成！快去看任务管理器，内存是不是涨了 1GB？");

        System.out.println("按回车键释放并退出...");
        System.in.read();
        buffer = null; // 让 buffer 变成垃圾
        System.gc();   // 手动触发 GC，归还直接内存（这一步先不解释，你只需要看现象）
        System.out.println("程序退出");
    }
}

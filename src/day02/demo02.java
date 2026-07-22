package day02;

import java.nio.ByteBuffer;

public class demo02 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("看任务管理器——准备分配 1GB 直接内存");
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024 * 1024 * 1024); // 1GB
        System.out.println("分配完了！看任务管理器，内存是不是涨了 1GB？");
        Thread.sleep(300000); // 停 5 分钟，让你慢慢看
        System.out.println("程序结束");
    }
}

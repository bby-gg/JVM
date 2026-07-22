package day02;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class demo01 {
    public static void main(String[] args) {
        System.out.println("===============demo01 start===============");
        //存放DiskCache的引用，防止被垃圾回收
        List<ByteBuffer> list = new ArrayList<>();
        int count = 0;
        try {
            while (true) {
                //分配一个1M的缓存
                ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024);
                //将缓存放入list中
                list.add(buffer);
                count++;
                System.out.println("已分配缓存：" + count + "个");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.err.println("=========发生异常=========");
            System.err.println("异常类型：" + e);
            System.err.println("异常信息：" + e.getMessage());
            System.err.println("程序崩溃前总共分配了" + count + "个缓存，每个缓存大小为1M");
            e.printStackTrace();
        }
    }
}

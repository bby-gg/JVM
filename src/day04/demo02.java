package day04;


import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * 验证软引用：内存紧张时自动回收
 * VM 参数：-Xmx20m -Xlog:gc
 */
public class demo02 {
    public static void main(String[] args) throws InterruptedException {
        // 1. 创建引用队列
        ReferenceQueue<byte[]> queue = new ReferenceQueue<>();
        // 2. 创建软引用
        byte[] data = new byte[1024 * 1024 * 6]; // 6MB
        SoftReference<byte[]> softRef = new SoftReference<>(data, queue);
        data = null;
        System.out.println("软引用对象是否存活：" + (softRef.get() != null));
        //3.不断创建强引用对象
        List<byte[]> list = new ArrayList<>();
        try {
            while (true) {
                list.add(new byte[1024 * 1024*2]);
                System.out.println("已分配 2MB，软引用还活着吗？" + (softRef.get() != null));
                Thread.sleep(300);
            }
        } catch (OutOfMemoryError e) {
            System.out.println("内存分配失败，程序退出");
        }
        // 4. 检查引用队列里有没有被回收的软引用
        Reference<? extends byte[]> ref = queue.poll();
        if (ref != null) {
            System.out.println("✅ 发现软引用已被回收！它已经被放入引用队列了。");
        } else {
            System.out.println("软引用尚未被回收，或者没有绑定队列。");
        }
    }
}

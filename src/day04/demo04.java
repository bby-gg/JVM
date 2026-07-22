package day04;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

/**
 * 虚引用  + 引用队列（带 VM 参数版本）
 * VM 参数：-Xmx20m -Xlog:gc
 */
public class demo04 {
    public static void main(String[] args) throws InterruptedException {
        //1.创建引用队列
        ReferenceQueue<byte[]> queue = new ReferenceQueue<>();
        //2.创建一个大对象
        byte[] data = new byte[1024*1024*5];
        PhantomReference<byte[]> phantomRef = new PhantomReference<>(data, queue);
        data = null;
        System.out.println("=== GC 前 ===");
        System.out.println("虚引用指向的对象：" + phantomRef.get()); // 永远返回 null
        System.out.println("引用队列中是否有元素：" + (queue.poll() != null));
        System.out.println("此时堆内存约占用 5MB（虚引用指向的大对象）");
        // 3. 手动触发 GC（会打印 GC 日志）
        System.out.println("\n=== 手动触发 GC（查看日志） ===");
        System.gc();
        Thread.sleep(500);
        // 4. GC 后检查
        System.out.println("\n=== GC 后 ===");
        System.out.println("虚引用指向的对象：" + phantomRef.get()); // 依然是 null
        Reference<?> ref = queue.poll();
        if (ref != null) {
            System.out.println(" 虚引用已被回收，并且已放入引用队列！");
            System.out.println("   （这说明 5MB 大对象已经被 GC 回收了）");
        } else {
            System.out.println(" 虚引用尚未被回收，或队列中没有元素。");
        }
        // 5. 再分配一些内存，看看 GC 日志的变化
        System.out.println("\n=== 继续分配内存，观察 GC 行为 ===");
        byte[] extra = new byte[1024 * 1024 * 8]; // 8MB
        System.out.println("分配了 8MB 新对象，此时堆内存使用约 8MB + 其他");
        Thread.sleep(1000);

        System.out.println("\n程序结束");
    }
}
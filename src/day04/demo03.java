package day04;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * 弱引用 + 引用队列（带 VM 参数版本）
 * VM 参数：-Xmx20m -Xlog:gc
 */
public class demo03 {
    public static void main(String[] args) throws InterruptedException {
        // 1. 创建引用队列
        ReferenceQueue<byte[]> queue = new ReferenceQueue<>();

        // 2. 创建弱引用，绑定队列
        byte[] data = new byte[1024 * 1024 * 5]; // 5MB，方便在 GC 日志里看到
        WeakReference<byte[]> weakRef = new WeakReference<>(data, queue);
        data = null; // 断开强引用，只剩弱引用

        System.out.println("=== GC 前 ===");
        System.out.println("弱引用对象是否存活：" + (weakRef.get() != null));
        System.out.println("引用队列中是否有元素：" + (queue.poll() != null));

        // 3. 手动触发 GC（会打印 GC 日志）
        System.out.println("\n=== 手动触发 GC，查看日志 ===");
        System.gc();
        Thread.sleep(500);

        // 4. GC 后检查
        System.out.println("\n=== GC 后 ===");
        System.out.println("弱引用对象是否存活：" + (weakRef.get() != null));
        Reference<? extends byte[]> ref = queue.poll();
        if (ref != null) {
            System.out.println("✅ 弱引用已被回收，并且已放入引用队列！");
        } else {
            System.out.println("弱引用未被回收，或队列中没有元素。");
        }
    }
}

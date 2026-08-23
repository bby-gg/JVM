package day10;  // 如果你没有 day10 这个包，可以删掉这一行，或者改成你的包名

/**
 * 手写 CAS 计数器（使用 synchronized 保证原子性）
 * 修正版：保证结果一定是 10000
 */
import java.util.concurrent.atomic.AtomicInteger;

public class MyCASDemo {
    private static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws Exception {
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    // ⭐ 真正的 CAS：由 CPU 指令保证原子性
                    count.incrementAndGet();
                }
            });
        }

        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();
        System.out.println("count = " + count); // 10000
    }
}
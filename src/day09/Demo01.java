package day09;

public class Demo01 {

    private static int count = 0;
    private static final int THREAD_COUNT = 10;
    private static final int TARGET = 100000;

    // 在 main 方法上直接 throws
    public static void main(String[] args) throws InterruptedException {
        System.out.println("========== JMM 原子性实验 ==========");
        System.out.println("线程数: " + THREAD_COUNT);
        System.out.println("每个线程累加次数: " + TARGET);
        System.out.println("期望结果: " + (THREAD_COUNT * TARGET));
        System.out.println("====================================\n");

        Thread[] threads = new Thread[THREAD_COUNT];

        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < TARGET; j++) {
                    count++;
                }
            }, "Worker-" + i);
            threads[i].start();
        }

        // 现在不会报错了，因为 main 方法声明了 throws
        for (Thread t : threads) {
            t.join();
        }

        int expected = THREAD_COUNT * TARGET;
        System.out.println("实际结果: " + count);
        System.out.println("期望结果: " + expected);
        System.out.println("丢失更新: " + (expected - count));
        System.out.println("丢失百分比: " + String.format("%.2f%%",
                (expected - count) * 100.0 / expected));
    }
}
package day09;

public class Demo02_Sync {
    private static int count = 0;
    private static final int THREAD_COUNT = 2;
    private static final int TARGET = 3;
    private static final Object LOCK = new Object();

    public static void main(String[] args) throws InterruptedException {
        System.out.println("期望结果: " + (THREAD_COUNT * TARGET) + "\n");

        Thread[] threads = new Thread[THREAD_COUNT];

        for (int i = 0; i < THREAD_COUNT; i++) {
            final int id = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < TARGET; j++) {
                    synchronized (LOCK) {  // ← 锁住，保证原子性
                        int old = count;
                        System.out.printf("  T%d 读取到 count = %d\n", id, old);

                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }

                        count = old + 1;
                        System.out.printf("  T%d 写入 count = %d\n", id, count);
                        System.out.println("  ---");
                    }
                }
            });
            threads[i].start();
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.println("\n实际结果: " + count);
        System.out.println("期望结果: " + (THREAD_COUNT * TARGET));
        System.out.println("丢失更新: " + ((THREAD_COUNT * TARGET) - count));
    }
}
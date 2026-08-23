package day10;

public class SynchronizedDemo {
    private static int count = 0;

    public static void main(String[] args) throws Exception {
        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    synchronized (SynchronizedDemo.class) {
                        count++;  // 加锁，保证原子性
                    }
                }
            });
        }

        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();

        System.out.println("count = " + count); // 一定是 10000
    }
}

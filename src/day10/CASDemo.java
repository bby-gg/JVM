package day10;

public class CASDemo {
    // 用 volatile 保证可见性（先记住这个，后面解释）
    private static volatile int count = 0;

    public static void main(String[] args) throws Exception {
        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    // 问题：count++ 不是原子操作，数据会错乱
                    count++;
                }
            });
        }

        for(Thread t : threads) t.start();
        for(Thread t : threads) t.join();

        System.out.println("count = " + count);
        // 期望 10000，实际可能 9876、9932... 永远小于 10000
    }
}

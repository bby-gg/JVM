package day09;

public class Demo4_1_SameCount {

    static int i = 0;
    static final int COUNT = 100;  // 每个线程执行100次

    public static void main(String[] args) throws InterruptedException {
        System.out.println("========== 连续运行20次，每次都打印 ==========");
        System.out.println("每个线程执行: " + COUNT + " 次");
        System.out.println("期望结果: 0");
        System.out.println("============================================\n");

        int correctCount = 0;
        int totalRuns = 20;

        for (int run = 0; run < totalRuns; run++) {
            i = 0;

            Thread t1 = new Thread(() -> {
                for (int j = 0; j < COUNT; j++) {
                    i++;
                    try {
                        Thread.sleep(1);  // 每次休眠1ms
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });

            Thread t2 = new Thread(() -> {
                for (int j = 0; j < COUNT; j++) {
                    i--;
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });

            t1.start();
            t2.start();
            t1.join();
            t2.join();

            // 每次都打印
            String status = (i == 0) ? "✅" : "❌";
            System.out.printf("第%2d次: %5d  %s%n", run + 1, i, status);

            if (i == 0) {
                correctCount++;
            }
        }

        System.out.println("\n============================================");
        System.out.printf("正确: %d/20 (%.0f%%)%n", correctCount, correctCount * 5.0);
        System.out.printf("错误: %d/20 (%.0f%%)%n", 20 - correctCount, (20 - correctCount) * 5.0);
    }
}
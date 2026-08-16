package day09;

/**
 * 用复杂计算触发重排序
 * 让 CPU 不得不重排序优化
 */
public class DemoOrdering {

    static int a = 0;
    static int b = 0;
    static int x = 0;
    static int y = 0;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("========== 用复杂计算触发重排序 ==========");
        System.out.println("每个线程执行耗时操作，让CPU重排序优化");
        System.out.println("==========================================\n");

        int totalRuns = 10000000;  // 10万次
        int reorderCount = 0;

        for (int run = 0; run < totalRuns; run++) {
            a = 0;
            b = 0;
            x = 0;
            y = 0;

            Thread t1 = new Thread(() -> {
                a = 1;
//                // 复杂计算，迫使 CPU 重排序
//                for (int i = 0; i < 1000; i++) {
//                    x = x + i - i;  // 无意义的计算，但消耗CPU
//                }
                x = b;
            });

            Thread t2 = new Thread(() -> {
                b = 1;
//                for (int i = 0; i < 1000; i++) {
//                    y = y + i - i;
//                }
                y = a;
            });

            t1.start();
            t2.start();
            t1.join();
            t2.join();

            if (x == 0 && y == 0) {
                reorderCount++;
                System.out.printf("第%d次: x=%d, y=%d  ⚠️ 重排序！%n", run + 1, x, y);
            }

            // 每10000次打印进度
            if ((run + 1) % 10000 == 0) {
                System.out.printf("进度: %d/%d, 重排序: %d次 (%.2f%%)%n",
                        run + 1, totalRuns, reorderCount, reorderCount * 100.0 / (run + 1));
            }
        }

        System.out.println("\n==========================================");
        System.out.printf("重排序次数: %d/%d (%.4f%%)%n",
                reorderCount, totalRuns, reorderCount * 100.0 / totalRuns);
    }
}
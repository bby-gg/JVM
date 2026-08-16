package day09;

/**
 * 用睡眠放大"读"和"写"之间的时间窗口
 * 让你亲眼看到原子性被破坏
 */
public class Demo02 {
    static int i = 0;
    static final int COUNT = 5000;  // 固定5000次

    public static void main(String[] args) throws InterruptedException {
        System.out.println("========== 同一次数(5000)，连续运行10次 ==========");
        System.out.println("期望结果: 0");
        System.out.println("================================================\n");

        int correctCount = 0;
        int totalRuns = 10;

        for (int run = 0; run < totalRuns; run++) {
            i = 0;  // 每次重置

            // 使用匿名内部类，兼容所有版本
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < COUNT; j++) {
                        i++;
                    }
                }
            });

            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < COUNT; j++) {
                        i--;
                    }
                }
            });

            t1.start();
            t2.start();
            t1.join();
            t2.join();

            // 格式化输出，固定宽度
            String result = String.format("%6d", i);
            String status = (i == 0) ? "✅ 正确(0)" : "❌ 错误(" + i + ")";
            System.out.printf("第%2d次: %s  %s%n", run + 1, result, status);

            if (i == 0) correctCount++;
        }

        System.out.println("\n=================================================");
        System.out.printf("正确次数: %d/10 (%.0f%%)%n", correctCount, correctCount * 10.0);
        System.out.printf("错误次数: %d/10 (%.0f%%)%n", 10 - correctCount, (10 - correctCount) * 10.0);
    }
}
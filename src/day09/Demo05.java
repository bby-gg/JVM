package day09;

public class Demo05 {
    static volatile boolean flag = true;  // 不加 volatile

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            System.out.println("T1 启动，开始循环...");
            while (flag) {
                // 一直循环，直到 flag = false
            }
            System.out.println("T1 结束循环！");
        });

        Thread t2 = new Thread(() -> {
            System.out.println("T2 启动，休眠 2 秒...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("T2 设置 flag = false");
            flag = false;
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("程序结束");
    }
}

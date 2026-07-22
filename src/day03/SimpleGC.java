package day03;

public class SimpleGC {
    public static void main(String[] args) {
        // 这是第一次造对象
        Object obj = new Object();
        System.out.println("第一次：obj 指向的对象是：" + obj);

        // 切断引用
        obj = null;
        System.out.println("第二次：obj 是 null，刚刚那个对象找不到了");

        // 第二次造对象
        obj = new Object();
        System.out.println("第三次：obj 现在指向一个全新的对象：" + obj);
        System.out.println("这个新对象和第一次的地址不一样，证明它不是同一个！");
    }
}

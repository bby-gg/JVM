package day06;

public class demo01 {
    // 1. 基本类型的静态变量
    private static int staticVar = 100;

    // 2. 实例变量（对象级别）
    private int instanceVar = 50;

    // 3. final常量
    public static final String CONSTANT = "Hello JVM";

    // 4. 构造方法
    public demo01() {
        this.instanceVar = 0;
    }

    // 5. 带参数的构造方法（重载）
    public demo01(int value) {
        this.instanceVar = value;
    }

    // 6. 静态方法
    public static int addStatic(int a, int b) {
        int sum = a + b;
        return sum * 2;
    }

    // 7. 实例方法
    public int multiply(int factor) {
        int result = this.instanceVar * factor;
        return result;
    }

    // 8. main方法（入口）
    public static void main(String[] args) {
        // 8.1 使用常量
        System.out.println(CONSTANT);

        // 8.2 创建对象cd C:\Users\bby\Desktop\JVM\demo\src\day06
        demo01 demo = new demo01(30);

        // 8.3 调用实例方法
        int product = demo.multiply(3);
        System.out.println("Product: " + product);

        // 8.4 调用静态方法
        int sum = addStatic(10, 20);
        System.out.println("Sum: " + sum);
    }
}
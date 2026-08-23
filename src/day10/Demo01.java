package day10;
/**
 * 逃逸分析性能对比Demo
 * 使用 -XX:+DoEscapeAnalysis（开启，默认） 和 -XX:-DoEscapeAnalysis（关闭）对比
 */

public class Demo01 {
    // 注意：为了看到GC，对象里加个数组，让对象变大一点，但依然可以在栈上分配（只要不逃逸）
    static class User {
        int id;
        byte[] data = new byte[128]; // 128字节
        public User(int id) { this.id = id; }
    }

    // 不逃逸：直接在方法内部创建并累加，对象不会被外部引用
    public static int testNoEscape(int id) {
        User user = new User(id);   // 尝试栈上分配
        return user.id;             // 返回基本类型，对象引用未传出
    }

    public static void main(String[] args) {
        // 预热JIT
        for (int i = 0; i < 20000; i++) { testNoEscape(i); }

        long start = System.currentTimeMillis();
        long sum = 0;
        // 循环 2000 万次
        for (int i = 0; i < 20_000_000; i++) {
            sum += testNoEscape(i);
        }
        long end = System.currentTimeMillis();

        System.out.println("耗时：" + (end - start) + " ms");
        System.out.println("Sum: " + sum); // 防止被优化掉
    }
}

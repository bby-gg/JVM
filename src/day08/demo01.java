package day08;

public class demo01 {
    public int add(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        demo01 demo = new demo01();
        int result = demo.add(1, 2);
        System.out.println(result);
    }
}

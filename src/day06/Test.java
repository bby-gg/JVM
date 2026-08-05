package day06;

public class Test {
    private int num = 10;

    public int add(int a, int b) {
        return a + b;
    }
    public static void main(String[] args) {
        Test t = new Test();
        int result = t.add(3, 5);
        System.out.println("3 + 5 = " + result);
    }
}

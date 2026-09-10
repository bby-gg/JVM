package day11;

public class Outer {

    private int num = 10;

    class Inner {
        public void show() {
            System.out.println("访问外部类私有字段 num = " + num);
        }
    }

    public static void main(String[] args) {
        Outer outer = new Outer();
        Outer.Inner inner = outer.new Inner();
        inner.show();
    }
}
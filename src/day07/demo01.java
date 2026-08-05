package day07;



class Parent{
    public static int a = 10;
    static {
        System.out.println("Parent 初始化了！");
    }
}
class Child extends Parent {
    public static int b = 20;
    static {
        System.out.println("Child 初始化了！");
    }
}
public class demo01 {
    public static void main(String[] args){
        System.out.println(Child.b);
    }
}

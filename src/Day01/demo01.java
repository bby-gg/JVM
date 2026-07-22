package Day01;

public class demo01 {
    public static void main(String[] args) {
        //场景1：字面量（存在字符串常量词）
        String s1 = "hello";
        String s2 = "hello";
        System.out.println("s1 == s2: "+ (s1 == s2));

        //场景2：new对象（强制在堆中创建对象）
        String s3 = new String("hello");
        System.out.println("s1 == s3: "+ (s1 == s3));

        //场景3：intern() - 将堆对象引用放入常量词
        String s4 = s3.intern();
        System.out.println("s1 == s4: "+ (s1 == s4));

        //场景4：动态拼接
        String s5 = "he" + "llo";
        String s6 = "he";
        String s7 =  s6 + "llo";
        System.out.println("s1 == s5: " + (s1 == s5));
        System.out.println("s1 == s7: " + (s1 == s7));

        //场景5：final修饰的变量
        final String s8 = "he";
        String s9 = s8 + "llo";
        System.out.println("s1 == s9: " + (s1 == s9));
    }
}

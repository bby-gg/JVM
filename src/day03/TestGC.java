package day03;

public class TestGC {
    TestGC ref;
    // 1. 静态变量是根，这个对象永远不会被回收
    private static TestGC staticRef = new TestGC();
    public static void main(String[] args) {
        //2.localRef是局部变量是根
        TestGC localRef = new TestGC();
        TestGC objA = new TestGC();
        TestGC objB = new TestGC();
        objA.ref = objB;
        objA = null;

    }
}

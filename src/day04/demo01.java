package day04;

import java.util.ArrayList;
import java.util.List;

/**
 * 强引用 3 种场景的验证代码
 * VM 参数：-Xmx20m -Xlog:gc（限制堆内存 20MB，方便看效果）
 */
public class demo01 {
    //场景2：成员变量（强引用）
    private static byte[] bigObj = new byte[1024*1024*5];
    public static void main(String[] args) throws InterruptedException {
        System.out.println("========== 强引用验证开始 ==========");
        System.out.println("当前堆内存限制：20MB");
        System.out.println("成员变量已占用 5MB（静态强引用）");

        //场景1：局部变量（强引用）
        System.out.println("========== 局部变量强引用验证开始 ==========");
        byte[] localObj = new byte[1024*1024*5];
        System.out.println("局部变量 localData 创建，占用 5MB（强引用）");
        System.out.println("此时总占用约 10MB，GC 不会回收任何对象");
        Thread.sleep(3000);

        //场景3：集合类（强引用）
        System.out.println("========== 集合类强引用验证开始 ==========");
        List<byte[]> list = new ArrayList<>();
        for(int i = 0;i<3;i++){
            byte[] bigObj = new byte[1024*1024*2];
            list.add(bigObj);
            System.out.println("集合添加第 " + (i + 1) + " 个 2MB 对象，当前总占用约 " + (10 + (i + 1) * 2) + "MB");
            Thread.sleep(1000);
        }
        System.out.println("集合已添加 3 个对象，总占用约 16MB（成员 5MB + 局部 5MB + 集合 6MB）");
        System.out.println("此时堆内存还有约 4MB 剩余，GC 依然不会回收任何强引用对象");
        try{
            byte[] extraObj = new byte[1024*1024*2];
            list.add(extraObj);
            System.out.println("分配成功！说明内存还够");
        }catch (Exception e){
            System.out.println("❌ 抛出 OutOfMemoryError！");
            System.out.println("证明：已有的 3 个强引用对象（成员、局部、集合）JVM 一个都没回收！");
            System.out.println("即使内存快不够了，强引用对象依然活着。");
        }
    }
}

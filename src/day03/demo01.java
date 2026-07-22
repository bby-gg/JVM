package day03;

import java.util.ArrayList;
import java.util.List;

/**
 * 看得见 GC 的演示代码
 * VM参数：-Xmx10m -Xms10m -XX:+PrintGCDetails
 */
public class demo01 {
    public static void main(String[] args) {
        List<byte[]> list = new ArrayList<>();
        System.out.println("开始不断添加对象");
        for(int i = 0;i<100;i++){
            byte[] bigObj = new byte[1024*1024];
            list.add(bigObj);
            System.out.println("已放入第 " + (i+1) + " 个对象");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("程序结束");
    }
}

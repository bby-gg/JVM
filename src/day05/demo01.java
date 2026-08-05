package day05;

import java.util.ArrayList;
import java.util.List;

/**
 * 新生代 vs 老年代 日志分析（精简版）
 * VM参数（JDK 9+）：-Xms20m -Xmx20m -Xlog:gc
 */
public class demo01 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("========== 新生代 vs 老年代 日志分析验证开始 ==========");
        List<byte[]> list = new ArrayList<>();
        for(int i = 0;i<20;i++){
            list.add(new byte[1024*1024]);
            if(i % 3 == 0){
                list.clear();
            }
            Thread.sleep(1000);
        }
        System.out.println("\n=== 阶段2：创建大对象，直接进入老年代 ===");
        byte[] bigObj = new byte[1024*1024*4];
        list.add(bigObj);
        Thread.sleep(3000);
        try{
            while(true){
                list.add(new byte[1024*1024]);
                Thread.sleep(1000);
            }
        }catch (OutOfMemoryError e){
            System.out.println("❌ 抛出 OutOfMemoryError！");
        }
    }
}

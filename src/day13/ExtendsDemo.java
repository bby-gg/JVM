package day13;

import java.util.Arrays;
import java.util.List;

public class ExtendsDemo {

    // ? extends Number：能读成 Number，不能写
    public static double sum(List<? extends Number> list) {
        double sum = 0;
        for (Number n : list) {
            sum += n.doubleValue();
        }
        // list.add(1);   // ❌ 编译错误
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(sum(Arrays.asList(1, 2, 3)));      // 6.0
        System.out.println(sum(Arrays.asList(1.1, 2.2)));     // 3.3
    }
}
package day13;

import java.util.ArrayList;
import java.util.List;

public class SuperDemo {

    // ? super Integer：能写 Integer，只能读成 Object
    public static void addAll(List<? super Integer> list) {
        list.add(1);
        list.add(2);
        // Integer i = list.get(0);  // ❌ 编译错误
    }

    public static void main(String[] args) {
        List<Integer> ints = new ArrayList<>();
        List<Number> nums = new ArrayList<>();
        List<Object> objs = new ArrayList<>();

        addAll(ints);
        addAll(nums);
        addAll(objs);

        System.out.println(ints);  // [1, 2]
        System.out.println(nums);  // [1, 2]
        System.out.println(objs);  // [1, 2]
    }
}
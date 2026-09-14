package day13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UnboundedDemo {

    public static void printAll(List<?> list) {
        for (Object o : list) {
            System.out.println(o);
        }
    }

    public static void main(String[] args) {
        printAll(Arrays.asList("a", "b"));
        printAll(Arrays.asList(1, 2));

        // List<Object> 和 List<?> 的区别
        List<String> strings = new ArrayList<>();
        List<?> list1 = strings;         // ✅
        // List<Object> list2 = strings; // ❌
    }
}
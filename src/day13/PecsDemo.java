package day13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PecsDemo {

    // src 是生产者（读）→ extends
    // dest 是消费者（写）→ super
    public static <T> void copy(List<? super T> dest, List<? extends T> src) {
        for (int i = 0; i < src.size(); i++) {
            dest.set(i, src.get(i));
        }
    }

    public static void main(String[] args) {
        List<Integer> src = Arrays.asList(1, 2, 3);

        List<Number> dest1 = new ArrayList<>(Arrays.asList(0, 0, 0));
        copy(dest1, src);
        System.out.println(dest1);   // [1, 2, 3]

        List<Object> dest2 = new ArrayList<>(Arrays.asList(null, null, null));
        copy(dest2, src);
        System.out.println(dest2);   // [1, 2, 3]
    }
}
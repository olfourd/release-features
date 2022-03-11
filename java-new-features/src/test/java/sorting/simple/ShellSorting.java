package sorting.simple;

import static sorting.SortingUtils.UNSORTED_ARRAY_WITH_REPLICAS;
import static sorting.SortingUtils.swap;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class ShellSorting {

    @Test
    void some() {
        int[] data = Arrays.copyOf(UNSORTED_ARRAY_WITH_REPLICAS, UNSORTED_ARRAY_WITH_REPLICAS.length);
        System.out.println(Arrays.toString(data));

        int gap = data.length / 2;

        while (gap >= 1) {
            for (int right = 0; right < data.length; right++) {
                // Смещаем правый указатель, пока не сможем найти такой, что
                // между ним и элементом до него не будет нужного промежутка
                for (int index = right - gap; index >= 0; index -= gap) {
                    if (data[index] > data[index + gap]) {
                        swap(data, index, index + gap);
                    }
                }
            }
            gap = gap / 2;
        }

        System.out.println(Arrays.toString(data));
    }
}

package sorting.simple;

import static sorting.SortingUtils.UNSORTED_ARRAY_WITH_REPLICAS;
import static sorting.SortingUtils.swap;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class SelectionSorting {

    @Test
    void some() {
        int[] data = Arrays.copyOf(UNSORTED_ARRAY_WITH_REPLICAS, UNSORTED_ARRAY_WITH_REPLICAS.length);
        System.out.println(Arrays.toString(data));

        for (int left = 0; left < data.length; left++) {
            int minIndex = left;

            for (int i = left; i < data.length; i++) {
                if (data[i] < data[minIndex]) {
                    minIndex = i;
                }
            }
            swap(data, left, minIndex);
        }

        System.out.println(Arrays.toString(data));
    }
}

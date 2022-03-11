package sorting;

import static sorting.SortingUtils.UNSORTED_ARRAY_WITH_REPLICAS;
import static sorting.SortingUtils.quickSort;
import static sorting.SortingUtils.swap;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class QuickSorting {

    @Test
    void some() {
        int[] data = Arrays.copyOf(UNSORTED_ARRAY_WITH_REPLICAS, UNSORTED_ARRAY_WITH_REPLICAS.length);
        System.out.println(Arrays.toString(data));

        quickSort(data, 0, data.length - 1);

        System.out.println(Arrays.toString(data));
    }
}

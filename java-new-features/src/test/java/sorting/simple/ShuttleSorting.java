package sorting.simple;

import static sorting.SortingUtils.UNSORTED_ARRAY_WITH_REPLICAS;
import static sorting.SortingUtils.swap;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

class ShuttleSorting {

    @Test
    void some() {
        int[] data = Arrays.copyOf(UNSORTED_ARRAY_WITH_REPLICAS, UNSORTED_ARRAY_WITH_REPLICAS.length);
        System.out.println(Arrays.toString(data));

        for (int index = 1; index < data.length; index++) {
            if (data[index] < data[index - 1]) {
                swap(data, index, index - 1);

                for (int z = index - 1; (z - 1) >= 0; z--) {
                    if (data[z] < data[z - 1]) {
                        swap(data, z, z - 1);
                    } else {
                        break;
                    }
                }
            }
        }

        System.out.println(Arrays.toString(data));
    }
}

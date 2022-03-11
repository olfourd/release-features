package sorting.simple;

import static sorting.SortingUtils.UNSORTED_ARRAY_WITH_REPLICAS;
import static sorting.SortingUtils.swap;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

class BubbleSorting {


    @Test
    void bubble() {
        int[] data = Arrays.copyOf(UNSORTED_ARRAY_WITH_REPLICAS, UNSORTED_ARRAY_WITH_REPLICAS.length);
        System.out.println(Arrays.toString(data));

        boolean needIteration = true;
        while (needIteration) {
            needIteration = false;
            for (int currentIndex = 1; currentIndex < data.length; currentIndex++) {
                int prevIndex = currentIndex - 1;
                if (data[currentIndex] < data[prevIndex]) {
                    swap(data, currentIndex, prevIndex);
                    needIteration = true;
                }
            }
        }

        System.out.println(Arrays.toString(data));
    }

    @Test
    void shaker() {
        int[] data = new int[] {1, 0, 5, 0, 2, 7, 9};
        System.out.println(Arrays.toString(data));

        int left = 0;
        int right = data.length - 1;

        while (left <= right) {
//            right part
            for (int index = right; index > left; --index) {
                int prev = index - 1;
                if (data[prev] > data[index]) {
                    swap(data, prev, index);
                }
            }
            ++left;

//              left part
            for (int index = left; index < right; ++index) {
                int next = index + 1;
                if (data[index] > data[next]) {
                    swap(data, index, next);
                }
            }
            --right;
        }
        System.out.println(Arrays.toString(data));
    }
}

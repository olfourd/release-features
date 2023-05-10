package sorting;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static sorting.SortingUtils.UNSORTED_ARRAY_WITH_REPLICAS;
import static sorting.SortingUtils.bubbleSort;
import static sorting.SortingUtils.insertionSort;
import static sorting.SortingUtils.mergeSort;
import static sorting.SortingUtils.quickSort;
import static sorting.SortingUtils.selectionSort;

public class CheckSortAlgorithmsTest {

    @Test
    void quickSortTest() {
        int[] input = Arrays.copyOf(UNSORTED_ARRAY_WITH_REPLICAS, UNSORTED_ARRAY_WITH_REPLICAS.length);
        int[] expected = Arrays.copyOf(input, input.length);
        Arrays.sort(expected, 0, input.length);

        quickSort(input, 0, input.length - 1);

        assertEquals("Arrays not equals", Arrays.toString(expected), Arrays.toString(input));
    }

    @Test
    void mergeSortTest() {
        int[] input = Arrays.copyOf(UNSORTED_ARRAY_WITH_REPLICAS, UNSORTED_ARRAY_WITH_REPLICAS.length);
        int[] expected = Arrays.copyOf(input, input.length);
        Arrays.sort(expected, 0, input.length);

        mergeSort(input, 0, input.length - 1);

        assertEquals("Arrays not equals", Arrays.toString(expected), Arrays.toString(input));
    }

    @Test
    void bubbleSortTest() {
        int[] input = Arrays.copyOf(UNSORTED_ARRAY_WITH_REPLICAS, UNSORTED_ARRAY_WITH_REPLICAS.length);
        int[] expected = Arrays.copyOf(input, input.length);
        Arrays.sort(expected, 0, input.length);

        bubbleSort(input);

        assertEquals("Arrays not equals", Arrays.toString(expected), Arrays.toString(input));
    }

    @Test
    void selectionSortTest() {
        int[] input = Arrays.copyOf(UNSORTED_ARRAY_WITH_REPLICAS, UNSORTED_ARRAY_WITH_REPLICAS.length);
        int[] expected = Arrays.copyOf(input, input.length);
        Arrays.sort(expected, 0, input.length);

        selectionSort(input);

        assertEquals("Arrays not equals", Arrays.toString(expected), Arrays.toString(input));
    }

    @Test
    void insertionSortTest() {
        int[] input = Arrays.copyOf(UNSORTED_ARRAY_WITH_REPLICAS, UNSORTED_ARRAY_WITH_REPLICAS.length);
        int[] expected = Arrays.copyOf(input, input.length);
        Arrays.sort(expected, 0, input.length);

        insertionSort(input);

        assertEquals("Arrays not equals", Arrays.toString(expected), Arrays.toString(input));
    }

}

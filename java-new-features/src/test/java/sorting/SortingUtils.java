package sorting;

import java.util.Arrays;
import java.util.Random;

public class SortingUtils {

    public static int[] UNSORTED_ARRAY_WITH_REPLICAS = new Random()
            .ints(10, 1, 10)
            .toArray();

    public static void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        int partition = partition(arr, left, right);
        quickSort(arr, left, partition - 1);
        quickSort(arr, partition + 1, right);
    }

    private static int partition(int[] arr, int start, int end) {
        int pivot = arr[end];
        int i = start - 1;

        for (int j = start; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        i++;
        swap(arr, end, i);

        return i;
    }

    public static void heapSort(int[] arr) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            shiftDown(arr, i, n);
        }

        for (int i = n - 1; i >= 0; i--) {
            swap(arr, 0, i);
            shiftDown(arr, 0, i);
        }
    }

    private static void shiftDown(int[] arr, int i, int n) {
        int largest = i, left = i * 2 + 1, right = i * 2 + 2;

        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != i) {
            swap(arr, largest, i);
            shiftDown(arr, largest, n);
        }
    }

    public static void swap(int[] arr, int from, int to) {
        int tmp = arr[from];
        arr[from] = arr[to];
        arr[to] = tmp;
    }

    public static void mergeSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        int middle = (left + right) / 2;
        mergeSort(arr, left, middle);
        mergeSort(arr, middle + 1, right);

        merge(arr, left, middle, right);
    }

    private static void merge(int[] arr, int left, int middle, int right) {
        int[] leftSub = Arrays.copyOfRange(arr, left, middle + 1);
        int[] rightSub = Arrays.copyOfRange(arr, middle + 1, right + 1);

        int k = left, i = 0, j = 0;

        while (i < leftSub.length && j < rightSub.length) {
            if (leftSub[i] < rightSub[j]) {
                arr[k] = leftSub[i];
                i++;
            } else {
                arr[k] = rightSub[j];
                j++;
            }
            k++;
        }

        while (i < leftSub.length) {
            arr[k] = leftSub[i];
            i++;
            k++;
        }

        while (j < rightSub.length) {
            arr[k] = rightSub[j];
            j++;
            k++;
        }
    }

    public static void bubbleSort(int[] arr) {
        boolean needIteration = true;
        while (needIteration) {
            needIteration = false;
            for (int cur = 1; cur < arr.length; cur++) {
                if (arr[cur] < arr[cur - 1]) {
                    swap(arr, cur, cur - 1);
                    needIteration = true;
                }
            }
        }
    }

    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }

            swap(arr, i, min);
        }
    }

    public static void insertionSort(int[] arr) {
        for (int left = 0; left < arr.length; left++) {
            int value = arr[left];                  // Вытаскиваем значение элемента
            int index = left - 1;                   // Перемещаемся по элементам, которые перед вытащенным элементом
            for (; index >= 0; index--) {
                if (value < arr[index]) {           // Если вытащили значение меньшее — передвигаем больший элемент дальше
                    arr[index + 1] = arr[index];
                } else {
                    break;                          // Если вытащенный элемент больше — останавливаемся
                }
            }
            arr[index + 1] = value;                 // В освободившееся место вставляем вытащенное значение
        }
    }
}
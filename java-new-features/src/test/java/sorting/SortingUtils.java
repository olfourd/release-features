package sorting;

import java.util.Random;

public class SortingUtils {

    public static int[] UNSORTED_ARRAY_WITH_REPLICAS = new Random()
            .ints(50, 1, 100)
            .toArray();

    public static void swap(int[] array, int ind1, int ind2) {
        int tmp = array[ind1];
        array[ind1] = array[ind2];
        array[ind2] = tmp;
    }

    public static void quickSort(int[] data, int leftBorder, int rightBorder) {
        int leftMarker = leftBorder;
        int rightMarker = rightBorder;
        int pivot = data[(leftMarker + rightMarker) / 2];

        do {
            // Двигаем левый маркер слева направо пока элемент меньше, чем pivot
            while (data[leftMarker] < pivot) {
                leftMarker++;
            }
            // Двигаем правый маркер, пока элемент больше, чем pivot
            while (data[rightMarker] > pivot) {
                rightMarker--;
            }

            // Проверим, не нужно обменять местами элементы, на которые указывают маркеры
            if (leftMarker <= rightMarker) {
                // Левый маркер будет меньше правого только если мы должны выполнить swap
                if (leftMarker < rightMarker) {
                    swap(data, leftMarker, rightMarker);
                }
                // Сдвигаем маркеры, чтобы получить новые границы
                leftMarker++;
                rightMarker--;
            }
        } while (leftMarker <= rightMarker);

        // Выполняем рекурсивно для частей
        if (leftMarker < rightBorder) {
            quickSort(data, leftMarker, rightBorder);
        }
        if (rightMarker > leftBorder) {
            quickSort(data, leftBorder, rightMarker);
        }
    }
}

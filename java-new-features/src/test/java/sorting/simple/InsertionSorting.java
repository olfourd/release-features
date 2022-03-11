package sorting.simple;

import static sorting.SortingUtils.UNSORTED_ARRAY_WITH_REPLICAS;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class InsertionSorting {

    @Test
    void some() {
        int[] data = Arrays.copyOf(UNSORTED_ARRAY_WITH_REPLICAS, UNSORTED_ARRAY_WITH_REPLICAS.length);
        System.out.println(Arrays.toString(data));

        for (int left = 0; left < data.length; left++) {
            // Вытаскиваем значение элемента
            int value = data[left];
            // Перемещаемся по элементам, которые перед вытащенным элементом
            int index = left - 1;
            for (; index >= 0; index--) {
                // Если вытащили значение меньшее — передвигаем больший элемент дальше
                if (value < data[index]) {
                    data[index + 1] = data[index];
                } else {
                    // Если вытащенный элемент больше — останавливаемся
                    break;
                }
            }
            // В освободившееся место вставляем вытащенное значение
            data[index + 1] = value;
        }

        System.out.println(Arrays.toString(data));
    }
}

package com.github.kuzznya.sort;

import java.util.ArrayList;
import java.util.List;

public class BubbleSort implements Sort {
    @Override
    public <T extends Comparable<T>> List<T> sort(List<T> source) {
        List<T> data = new ArrayList<>(source);

        for (int i = 0; i < data.size() - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < data.size() - i - 1; j++) {
                if (data.get(j).compareTo(data.get(j + 1)) > 0) {
                    T temp = data.get(j);
                    data.set(j, data.get(j + 1));
                    data.set(j + 1, temp);
                    swapped = true;
                }
            }
            if (!swapped)
                break;
        }

        return data;
    }
}

package com.github.kuzznya.sort;

import java.util.ArrayList;
import java.util.List;

public class SelectionSort implements Sort {
    @Override
    public <T extends Comparable<T>> List<T> sort(List<T> source) {
        List<T> data = new ArrayList<>(source);

        for (int i = 0; i < data.size() - 1; i++) {
            int indexOfMin = i;
            for (int j = i + 1; j < data.size(); j++) {
                if (data.get(j).compareTo(data.get(indexOfMin)) < 0)
                    indexOfMin = j;
            }
            T temp = data.get(i);
            data.set(i, data.get(indexOfMin));
            data.set(indexOfMin, temp);
        }

        return data;
    }
}

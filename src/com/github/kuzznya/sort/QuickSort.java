package com.github.kuzznya.sort;

import java.util.ArrayList;
import java.util.List;

public class QuickSort implements Sort {

    private <T extends Comparable<T>> void quickSort(List<T> data, int left, int right) {
        T key = data.get(left + (int) (Math.random() * (right - left)));

        int i = left;
        int j = right;

        while (i < j) {
            while (data.get(i).compareTo(key) < 0)
                i++;
            while (data.get(j).compareTo(key) > 0)
                j--;

            if (i <= j) {
                T temp = data.get(i);
                data.set(i, data.get(j));
                data.set(j, temp);
                i++;
                j--;
            }
        };

        if (j > left)
            quickSort(data, left, j);
        if (i < right)
            quickSort(data, i, right);
    }

    @Override
    public <T extends Comparable<T>> List<T> sort(List<T> source) {
        List<T> data = new ArrayList<>(source);
        quickSort(data, 0, data.size() - 1);
        return data;
    }
}

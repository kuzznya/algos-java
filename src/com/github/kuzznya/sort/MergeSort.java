package com.github.kuzznya.sort;

import java.util.ArrayList;
import java.util.List;

public class MergeSort implements Sort {

    private <T extends Comparable<T>> List<T> merge(List<T> sortedData1, List<T> sortedData2) {
        int i1 = 0, i2 = 0;

        List<T> result = new ArrayList<>();

        while (i1 < sortedData1.size() && i2 < sortedData2.size()) {
            if (sortedData1.get(i1).compareTo(sortedData2.get(i2)) <= 0)
                result.add(sortedData1.get(i1++));
            else
                result.add(sortedData2.get(i2++));
        }

        while (i1 < sortedData1.size())
            result.add(sortedData1.get(i1++));
        while (i2 < sortedData2.size())
            result.add(sortedData2.get(i2++));

        return result;
    }

    @Override
    public <T extends Comparable<T>> List<T> sort(List<T> source) {
        if (source.size() <= 1)
            return source;

        return merge(
                sort(source.subList(0, source.size() / 2)),
                sort(source.subList(source.size() / 2, source.size()))
        );
    }
}

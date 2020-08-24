package com.github.kuzznya.sort;

import java.util.ArrayList;
import java.util.List;

public class SortTester {

    public static <T extends Comparable<T>> boolean isSorted(List<T> data) {
        for (int i = 0; i < data.size() - 1; i++) {
            if (data.get(i).compareTo(data.get(i + 1)) > 0)
                return false;
        }
        return true;
    }

    public static int test(Sort sort, int dataSize) {
        List<Integer> data = new ArrayList<>(dataSize);
        for (int i = 0; i < dataSize; i++)
            data.add((int) (Math.random() * 1000));

        long time0 = System.currentTimeMillis();

        List<Integer> sorted = sort.sort(data);

        long time1 = System.currentTimeMillis();

        if (!isSorted(sorted))
            throw new SortCheckException();

        return (int) (time1 - time0);
    }

    public static class SortCheckException extends RuntimeException {
        public SortCheckException() {
            super("Invalid sort result");
        }
    }
}

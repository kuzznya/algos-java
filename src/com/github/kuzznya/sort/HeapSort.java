package com.github.kuzznya.sort;

import java.util.ArrayList;
import java.util.List;

public class HeapSort implements Sort {

    private <T extends Comparable<T>> void siftDown(List<T> heap, int heapSize, int idxToSift) {
        int leftChildIdx = idxToSift * 2 + 1;
        int rightChildIdx = idxToSift * 2 + 2;

        int idxToSwap = idxToSift;

        if (leftChildIdx < heapSize && heap.get(idxToSwap).compareTo(heap.get(leftChildIdx)) < 0)
            idxToSwap = leftChildIdx;
        if (rightChildIdx < heapSize && heap.get(idxToSwap).compareTo(heap.get(rightChildIdx)) < 0)
            idxToSwap = rightChildIdx;

        if (idxToSwap != idxToSift) {
            T temp = heap.get(idxToSwap);
            heap.set(idxToSwap, heap.get(idxToSift));
            heap.set(idxToSift, temp);

            siftDown(heap, heapSize, idxToSwap);
        }
    }

    private <T extends Comparable<T>> void buildHeap(List<T> data) {
        for (int i = data.size() / 2; i >= 0; i--)
            siftDown(data, data.size(), i);
    }

    @Override
    public <T extends Comparable<T>> List<T> sort(List<T> source) {
        List<T> data = new ArrayList<>(source);

        buildHeap(data);

        int heapSize = data.size();
        for (int i = 0; i < data.size() - 1; i++) {
            T temp = data.get(0);
            data.set(0, data.get(heapSize - 1));
            data.set(heapSize - 1, temp);

            siftDown(data, --heapSize, 0);
        }

        return data;
    }
}

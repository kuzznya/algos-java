package com.github.kuzznya.ds.list;

public interface MyList<T> {
    T get(int idx);
    void set(int idx, T value);
    void add(T value);
    void remove(int idx);
    void clear();
    int size();
    void reverse();
}

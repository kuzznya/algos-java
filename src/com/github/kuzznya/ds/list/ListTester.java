package com.github.kuzznya.ds.list;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListTester {
    public static int test(Class<? extends MyList<Integer>> listClass, int dataSize) {
        long time0 = System.currentTimeMillis();

        MyList<Integer> list;
        try {
            list = listClass.getDeclaredConstructor().newInstance();
        } catch (ReflectiveOperationException ex) {
            throw new MyListTestException("Cannot create new instance of class");
        }

        for (int i = 0; i < dataSize; i++)
            list.add(i);

        if (list.size() != dataSize)
            throw new MyListTestException("Invalid size() value");

        for (int i = 0; i < list.size() / 2; i++) {
            int idx = (int) (Math.random() * (list.size() - 1));
            if (list.get(idx) != idx)
                throw new MyListTestException("get() returns incorrect value " + list.get(idx) + " instead of " + idx);
        }

        Set<Integer> removed = new HashSet<>();
        for (int i = 0; i < list.size() / 2; i++) {
            int idx = (int) (Math.random() * (list.size() - 1));
            removed.add(list.get(idx));
            list.remove(idx);
        }
        for (int i = 0; i < list.size(); i++) {
            if (removed.contains(list.get(i)))
                throw new MyListTestException("Element " + list.get(i) + " was not removed after remove() call");
        }

        for (int i = 0; i < list.size() / 2; i++) {
            int idx = (int) (Math.random() * (list.size() - 1));
            list.set(idx, 0);
            if (list.get(idx) != 0)
                throw new MyListTestException("set() does not sets correct value");
        }

        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < list.size(); i++)
            data.add(list.get(list.size() - i - 1));

        list.reverse();

        for (int i = 0; i < list.size(); i++) {
            if (!data.get(i).equals(list.get(i)))
                throw new MyListTestException("Invalid reverse");
        }

        long time1 = System.currentTimeMillis();

        return (int) (time1 - time0);
    }

    public static class MyListTestException extends RuntimeException {
        public MyListTestException() {
            super("Error occurred while trying to test MyList implementation");
        }
        public MyListTestException(String message) {
            super(message);
        }
    }
}

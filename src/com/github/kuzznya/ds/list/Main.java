package com.github.kuzznya.ds.list;

import java.util.Scanner;

public class Main {

    @SuppressWarnings("unchecked")
    public static Class<MyList<Integer>> loadMyListClassByName(String name) {
        Package listPackage = MyList.class.getPackage();
        try {
            return (Class<MyList<Integer>>) Class.forName(listPackage.getName() + "." + name);
        } catch (Exception ex) {
            throw new RuntimeException("Cannot load MyList implementation with name " + name, ex);
        }
    }

    public static void execute(Class<MyList<Integer>> listClass, int dataSize, int count) {
        System.out.println(listClass.getSimpleName() + " test, " + dataSize + " elements");

        int timeSum = 0;
        for (int i = 0; i < count; i++) {
            final int timePassed = ListTester.test(listClass, dataSize);
            timeSum += timePassed;
            System.out.println(timePassed + " ms (test " + (i + 1) + ")");
        }

        System.out.println("\n" + count + " tests passed, average time is " + timeSum / count + " ms");
    }

    public static void main(String[] args) {
        final int executionCount = 100;

        Scanner in = new Scanner(System.in);

        System.out.print("Enter MyList implementation name (e.g. SinglyLinkedList): ");
        String listName = in.nextLine();

        System.out.print("Enter data size: ");
        int dataSize = in.nextInt();

        execute(loadMyListClassByName(listName), dataSize, executionCount);
    }
}

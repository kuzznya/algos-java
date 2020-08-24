package com.github.kuzznya.sort;

import com.github.kuzznya.sort.Sort;
import com.github.kuzznya.sort.SortTester;

import java.util.Scanner;

public class Main {

    public static Sort loadSortByName(String name) {
        Package sortPackage = Sort.class.getPackage();
        try {
            Class<?> sortClass = Class.forName(sortPackage.getName() + "." + name);
            return (Sort) sortClass.getDeclaredConstructor().newInstance();
        } catch (Exception ex) {
            throw new RuntimeException("Cannot find sort with name " + name, ex);
        }
    }

    public static void execute(Sort sort, int dataSize, int count) {
        System.out.println(sort.getClass().getSimpleName() + " test, " + dataSize + " elements");

        int timeSum = 0;
        for (int i = 0; i < count; i++) {
            final int timePassed = SortTester.test(sort, dataSize);
            timeSum += timePassed;
            System.out.println(timePassed + " ms (test " + (i + 1) + ")");
        }

        System.out.println("\n" + count + " tests passed, average sorting time is " + timeSum / count + " ms");
    }

    public static void main(String[] args) {
        final int executionCount = 100;

        Scanner in = new Scanner(System.in);

        System.out.print("Enter sort name (e.g. BubbleSort): ");
        String sortName = in.nextLine();

        System.out.print("Enter data size: ");
        int dataSize = in.nextInt();

        Sort sort = loadSortByName(sortName);

        try {
            execute(sort, dataSize, executionCount);
        } catch (SortTester.SortCheckException ex) {
            System.err.println("Error - invalid sort");
            return;
        }
    }
}

// File Name: QuickSorter.java
// Author: Vedant Mahajan
// Course : CS 3345.004
// Modification History
// This code was written on 22nd April 2020.

import java.lang.Exception;
import java.util.ArrayList;
import java.time.Duration;
import java.util.Arrays;
import java.util.Random;

public class QuickSorter {

    private static final int LIMIT = 20;

    public static enum PivotStrategy {
        FIRST_ELEMENT, //First element as pivot
        RANDOM_ELEMENT, //Randomly choosing the pivot element
        MEDIAN_OF_THREE_RANDOM_ELEMENTS, // Choosing the median of 3 randomly chosen elements as the pivot
        MEDIAN_OF_THREE_ELEMENTS //Median of first, center and last element
    }

    // This method returns the duration of each sorting algorithm
    public static <E extends Comparable<E>> Duration timedQuickSort(ArrayList<E> list, PivotStrategy pivotStrategy) throws NullPointerException {
        // throws an exception if element to be entered is null

        if (list == null) {
            throw new NullPointerException("The list entered is null !");
        } else {
            long start = System.nanoTime();
            QuickSorter.quickSort(list, pivotStrategy, 0, list.size() - 1);
            long finish = System.nanoTime();
            return Duration.ofNanos(finish - start);
        }

    }

    /*
     * This method uses insertion sort for array sie < 20
     */
    public static <E extends Comparable<E>> void insertionSort(ArrayList<E> list, int l, int r) {

        for (int i = 1; i <= r; ++i) {

            E key = list.get(i);
            int j = i - 1;

            while (j >= 0 && list.get(j).compareTo(key) > 0) {

                list.set(j + 1, list.get(j));
                j = j - 1;
            }

            list.set(j + 1, key);
        }

    }

    /*
     * This method recursively performs quick sort for different pivot
     * strategies
     */
    public static <E extends Comparable<E>> void quickSort(ArrayList<E> list, PivotStrategy pivotStrategy, int l, int r) {

        if (l >= r) {
            return;
        }

        //Insertion sort is used for array size < 20
        if (r - l < LIMIT) {
            QuickSorter.insertionSort(list, l, r);
        } //Quick sort is used recursively for array size >= 20
        else {

            E p = QuickSorter.findPivot(list, pivotStrategy, l, r);

            int i = l;
            int j = r - 1;

            while (i < j) {

                while (list.get(i).compareTo(p) < 0 && (i + 1 <= r)) {
                    i++;
                }

                while (list.get(j).compareTo(p) > 0 && (j - 1 >= l)) {
                    j--;
                }

                if (i < j) {
                    swapElements(list, i, j);
                }

            }

            swapElements(list, i, r);

            //recursive method for quick sort
            QuickSorter.quickSort(list, pivotStrategy, l, i - 1);
            QuickSorter.quickSort(list, pivotStrategy, i + 1, r);

        }

    }

    /*
     * Method to swap elements
     */
    public static <E extends Comparable<E>> void swapElements(ArrayList<E> list, int x, int y) {
        E temp = list.get(x);
        list.set(x, list.get(y));
        list.set(y, temp);
    }

    /*
     * This method finds the pivot using different strategies
     */
    public static <E extends Comparable<E>> E findPivot(ArrayList<E> list, PivotStrategy pivotStrategy, int l, int r) {
        switch (pivotStrategy) {
            case FIRST_ELEMENT:

                return list.get(l);

            case RANDOM_ELEMENT:

                Random rand = new Random();
                return list.get(rand.nextInt(r));

            case MEDIAN_OF_THREE_ELEMENTS:

                //find index of middle element of the array
                int middle = (l + r) / 2;
                //find index of middle element of the array
                int pivot = (l + r + middle) / 3;

                return list.get(pivot);

            case MEDIAN_OF_THREE_RANDOM_ELEMENTS:

                Random rand1 = new Random();
                return list.get((rand1.nextInt(r)
                        + rand1.nextInt(r)
                        + rand1.nextInt(r)) / 3);

            default:
                System.out.println("Invalid strategy used");
        }

        return list.get(0);
    }

    /*
     * Method to generate a list using random elements
     */
    public static ArrayList<Integer> generateRandomList(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException();
        } else {

            Random rand = new Random();
            ArrayList<Integer> arr = new ArrayList<Integer>();
            for (int x = 0; x < size; x++) {
                arr.add(rand.nextInt());

            }

            return arr;
        }
    }

}

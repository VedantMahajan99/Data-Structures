// File Name: Main.java
// Author: Vedant Mahajan
// Course : CS 3345.004
// Modification History
// This code was written on 22nd April 2020.


import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.IOException;
import java.io.PrintWriter;

class Main {

    public static void main(String[] args) throws IOException {

        if (args.length == 4) {
            //All of the code is enclosed in this bracket of code
            try {
                int size = Integer.parseInt(args[0]);

                String fileReport = args[1];
                String fileUnsorted = args[2];
                String fileSorted = args[3];

                PrintWriter printReports = new PrintWriter(fileReport);
                PrintWriter printUnsorted = new PrintWriter(fileUnsorted);
                PrintWriter printSorted = new PrintWriter(fileSorted);

                ArrayList<Integer> array = QuickSorter.generateRandomList(size);
                if (array == null) {
                    throw new NullPointerException("The list entered is null !");
                }

                //Sorts the array list
                //Collections.sort(array); 
                //Creating copies of the array for different techniques
                ArrayList<Integer> arrayCopy1 = new ArrayList<Integer>(array);
                ArrayList<Integer> arrayCopy2 = new ArrayList<Integer>(array);
                ArrayList<Integer> arrayCopy3 = new ArrayList<Integer>(array);
                ArrayList<Integer> arrayCopy4 = new ArrayList<Integer>(array);

                //printing the unsorted array to a file
                printUnsorted.println(array);

                //Printing the report 
                printReports.println("Array Size = " + size);
                printReports.println("FIRST_ELEMENT  :  " + QuickSorter.timedQuickSort(arrayCopy1, QuickSorter.PivotStrategy.FIRST_ELEMENT));

                printReports.println("RANDOM_ELEMENT  :  " + QuickSorter.timedQuickSort(arrayCopy2, QuickSorter.PivotStrategy.RANDOM_ELEMENT));
                printReports.println("MEDIAN_OF_THREE_RANDOM_ELEMENT  :  " + QuickSorter.timedQuickSort(arrayCopy3, QuickSorter.PivotStrategy.MEDIAN_OF_THREE_RANDOM_ELEMENTS));
                printReports.println("MEDIAN_OF_THREE_ELEMENTS  :  " + QuickSorter.timedQuickSort(arrayCopy4, QuickSorter.PivotStrategy.MEDIAN_OF_THREE_ELEMENTS));

                //Printing the sorted array using different techniques to a file
                printSorted.println(arrayCopy1);
                printSorted.println(arrayCopy2);
                printSorted.println(arrayCopy3);
                printSorted.println(arrayCopy4);

                //Closing all the files
                printReports.close();
                printUnsorted.close();
                printSorted.close();

            } catch (Exception e) {
                System.out.println("Error: " + e);
            }

        } else {
            System.out.println("Error: Number of Arguments is not 4");
        }

    }
}

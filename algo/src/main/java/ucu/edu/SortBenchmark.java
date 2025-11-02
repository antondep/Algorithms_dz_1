package ucu.edu;

import java.util.*;

public class SortBenchmark {
 
    public static void compareSorting(List<Student> original) {
        List<Student> listStandard = new ArrayList<>(original);
        List<Student> listCustom = new ArrayList<>(original);

        long start1 = System.nanoTime();
        SortBySurnameName.sortAndSave(listStandard, "sorted_standard.csv");
        long time1 = System.nanoTime() - start1;

        long start2 = System.nanoTime();
        SortBySurnameName.customSortAndSave(listCustom, "sorted_custom.csv");
        long time2 = System.nanoTime() - start2;

        System.out.println("\nSorting Benchmark");
        System.out.printf("Standard sort time: %.3f ms%n", time1 / 1_000_000.0);
        System.out.printf("Custom sort time:   %.3f ms%n", time2 / 1_000_000.0);
    }
}
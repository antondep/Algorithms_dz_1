package ucu.edu;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class main1 {
    public static void main(String[] args) {
        StudentDatabase db = new StudentDatabase();
        db.loadFromCSV("students.csv");
        System.out.println("\nSort");
        SortBenchmark.compareSorting(db.getStudents());

        int[] sizes = { 100, 1000, 10_000, 100_000 };
        List<ExperimentResult> results = new ArrayList<>();

        for (int size : sizes) {
            System.out.println("\nRunning experiment for " + size + " students");
            String path = "datasets/students_" + size + ".csv"; 
            ExperimentResult result = ExperimentRunner.runExperiment(path);
            results.add(result);
            System.out.println(result);
        }

        saveResultsToCSV(results, "experiment_results.csv");
        System.out.println("\nResults saved to experiment_results.csv");

    }

    private static void saveResultsToCSV(List<ExperimentResult> results, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("DataSize,TotalOps,Op1,Op2,Op3,MemoryMB\n");
            for (ExperimentResult r : results) {
                writer.write(String.format("%d,%d,%d,%d,%d,%d\n",
                        r.dataSize, r.totalOps, r.op1, r.op2, r.op3, r.memoryMB));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
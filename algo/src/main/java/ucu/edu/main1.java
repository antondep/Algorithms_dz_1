package ucu.edu;

import java.io.FileWriter;
import java.io.IOException;

public class main1 {
    public static void main(String[] args) throws IOException { 
        StudentDatabase db = new StudentDatabase();
        db.loadFromCSV("students.csv");

        System.out.println("\nSorting Comparison");
        SortBenchmark.compareSorting(db.getStudents());

        try {
            String path = "students.csv";
            if (args != null && args.length > 0)
                path = args[0];

            System.out.println("\nRunning experiment");
            ExperimentRunner.runExperimentWithContainers(path);

            System.out.println("\nExperiment finished. Results saved to experiment_results.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
package ucu.edu;
import java.util.*;

public class ExperimentRunner {

    private static final int TIME_LIMIT_MS = 10_000;
    private static final Random rand = new Random();

    public static ExperimentResult runExperiment(String csvPath) {
        StudentDatabase db = new StudentDatabase();
        db.loadFromCSV(csvPath);

        List<Student> students = db.getStudents();
        Map<String, Student> emailIndex = db.getEmailIndex();
        Map<String, List<Student>> groupIndex = db.getGroupIndex();

        long startTime = System.currentTimeMillis();
        long endTime = startTime + TIME_LIMIT_MS;

        long op1 = 0, op2 = 0, op3 = 0;

        while (System.currentTimeMillis() < endTime) {
            int choice = rand.nextInt(31);
            if (choice == 0) {
                Top100.getTop100(students);
                op1++;
            } else if (choice <= 10) {
                Student s = students.get(rand.nextInt(students.size()));
                float newRating = rand.nextFloat() * 100;
                SetRating.setRating(emailIndex, s.getEmail(), newRating);
                op2++;
            } else { 
                BestGroup.findBestGroup(groupIndex);
                op3++;
            }
        }

        long totalOps = op1 + op2 + op3;
        long usedMemory = getUsedMemoryMB();

        return new ExperimentResult(students.size(), op1, op2, op3, totalOps, usedMemory);
    }

    private static long getUsedMemoryMB() {
        Runtime runtime = Runtime.getRuntime();
        return (runtime.totalMemory() - runtime.freeMemory()) / (1024 * 1024);
    }
}
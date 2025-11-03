package ucu.edu;

import java.io.*;
import java.util.*;

public class ExperimentRunner {

    private static final Random random = new Random();

    public static void runExperimentWithContainers(String csvPath) throws IOException {
        String[] containerTypes = { "ArrayList", "HashSet", "ArrayDeque" };
        int[] sizes = { 100, 1000, 10000, 100000 };

        try (FileWriter writer = new FileWriter("experiment_results.csv")) {
            writer.write("Container,Size,Op1Count,Op2Count,Op3Count,TotalOps,MemoryMB\n");

            for (String type : containerTypes) {
                System.out.println("\nContainer: " + type);
                for (int size : sizes) {
                    runExperimentForSize(type, size, csvPath, writer);
                }
            }
        }
    }

    private static void runExperimentForSize(String containerType, int size,
            String csvPath, FileWriter writer) throws IOException {

        List<Student> students = StudentDatabase.loadSample(csvPath, size);
        StudentDatabase db = new StudentDatabase();
        for (Student s : students) {
            db.getStudentsList().add(s);
            db.getEmailIndex().put(s.getEmail(), s);
            db.getGroupIndex().computeIfAbsent(s.getGroup(), g -> new ArrayList<>()).add(s);
        }

        Collection<Student> container = createContainer(containerType, students);

        long op1Count = 0, op2Count = 0, op3Count = 0;
        long totalCount = 0;

        long start = System.currentTimeMillis();
        long endTime = start + 10_000; 

        while (System.currentTimeMillis() < endTime) {
            int op = chooseOperation(); 
            switch (op) {
                case 1:
                    if (containerType.equals("ArrayList"))
                        Top100.getTop100UsingList((List<Student>) container);
                    else if (containerType.equals("HashSet"))
                        Top100.getTop100UsingSet((Set<Student>) container);
                    else
                        Top100.getTop100UsingQueue(container);
                    op1Count++;
                    break;

                case 2:
                    if (containerType.equals("HashSet") || containerType.equals("ArrayList"))
                        BestGroup.findBestGroupUsingHashMap(db.getGroupIndex());
                    else
                        BestGroup.findBestGroupUsingQueue(db.getGroupIndex());
                    op2Count++;
                    break;

                case 3:
                    if (students.isEmpty())
                        break;
                    Student randomStudent = students.get(random.nextInt(students.size()));
                    String email = randomStudent.getEmail();
                    float newRating = random.nextFloat() * 100;

                    if (containerType.equals("ArrayList"))
                        SetRating.setRatingUsingHashMap(db.getEmailIndex(), email, newRating);
                    else if (containerType.equals("ArrayDeque"))
                        SetRating.setRatingUsingQueue(new ArrayDeque<>(db.getStudentsList()), email, newRating);
                    else
                        SetRating.setRatingUsingSet(new HashSet<>(db.getStudentsList()), email, newRating);
                    op3Count++;
                    break;
            }
            totalCount++;
        }

        double memoryUsed = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())
                / (1024.0 * 1024.0);

        System.out.printf(Locale.US,
                "Size=%-6d Op1=%-6d Op2=%-6d Op3=%-6d Total=%-6d Mem=%.2f MB%n",
                size, op1Count, op2Count, op3Count, totalCount, memoryUsed);

        writer.write(String.format(Locale.US,
                "%s,%d,%d,%d,%d,%d,%.2f\n",
                containerType, size, op1Count, op2Count, op3Count, totalCount, memoryUsed));
        writer.flush();
    }

    private static Collection<Student> createContainer(String type, List<Student> data) {
        switch (type) {
            case "ArrayList":
                return new ArrayList<>(data);
            case "HashSet":
                return new HashSet<>(data);
            case "ArrayDeque":
                return new ArrayDeque<>(data);
            default:
                throw new IllegalArgumentException("Unknown container: " + type);
        }
    }

    private static int chooseOperation() {
        int r = random.nextInt(31);
        if (r == 0)
            return 1;
        else if (r <= 10)
            return 2;
        else
            return 3;
    }
}
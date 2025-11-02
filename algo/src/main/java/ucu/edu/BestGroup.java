package ucu.edu;

import java.util.*;

public class BestGroup {
    public static String findBestGroup(Map<String, List<Student>> groupIndex) {
        String bestGroup = null;
        double bestAvg = 0;

        for (Map.Entry<String, List<Student>> entry : groupIndex.entrySet()) {
            List<Student> group = entry.getValue();
            double avg = group.stream().mapToDouble(Student::getRating).average().orElse(0);
            if (avg > bestAvg) {
                bestAvg = avg;
                bestGroup = entry.getKey();
            }
        }

        return bestGroup;
    }
}
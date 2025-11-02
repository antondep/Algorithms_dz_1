package ucu.edu;

import java.util.*;
import java.util.stream.Collectors;

public class Top100 {
    public static List<Student> getTop100(List<Student> students) {
        return students.stream()
                .sorted(Comparator.comparingDouble(Student::getRating).reversed())
                .limit(100)
                .collect(Collectors.toList());
    }
}

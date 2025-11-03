package ucu.edu;

import java.util.*;
import java.util.stream.Collectors;

public class Top100 {

    public static List<Student> getTop100UsingList(List<Student> students) {
        return students.stream()
                .sorted(Comparator.comparingDouble(Student::getRating).reversed()
                        .thenComparing(Student::getEmail))
                .limit(100)
                .collect(Collectors.toList());
    }

    public static List<Student> getTop100UsingQueue(Collection<Student> students) {
        PriorityQueue<Student> minHeap = new PriorityQueue<>(
                Comparator.comparingDouble(Student::getRating)
                        .thenComparing(Student::getEmail));

        for (Student s : students) {
            if (minHeap.size() < 100) {
                minHeap.add(s);
            } else if (s.getRating() > Objects.requireNonNull(minHeap.peek()).getRating()) {
                minHeap.poll();
                minHeap.add(s);
            }
        }

        List<Student> result = new ArrayList<>(minHeap);
        result.sort(Comparator.comparingDouble(Student::getRating).reversed()
                .thenComparing(Student::getEmail));
        return result;
    }

    public static List<Student> getTop100UsingHashMap(Collection<Student> students) {
        Map<String, Student> bestByEmail = new HashMap<>();
        for (Student s : students) {
            Student prev = bestByEmail.get(s.getEmail());
            if (prev == null || s.getRating() > prev.getRating()) {
                bestByEmail.put(s.getEmail(), s);
            }
        }

        return bestByEmail.values().stream()
                .sorted(Comparator.comparingDouble(Student::getRating).reversed()
                        .thenComparing(Student::getEmail))
                .limit(100)
                .collect(Collectors.toList());
    }

    public static List<Student> getTop100UsingSet(Collection<Student> students) {
        Comparator<Student> cmp = Comparator
                .comparingDouble(Student::getRating).reversed()
                .thenComparing(Student::getEmail);

        TreeSet<Student> sorted = new TreeSet<>(cmp);
        for (Student s : students)
            sorted.add(s);

        List<Student> result = new ArrayList<>();
        int count = 0;
        for (Student s : sorted) {
            result.add(s);
            if (++count >= 100)
                break;
        }
        return result;
    }

    public static List<Student> getTop100(List<Student> students) {
        return getTop100UsingList(students);
    }
}
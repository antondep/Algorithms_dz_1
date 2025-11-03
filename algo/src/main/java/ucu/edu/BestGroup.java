package ucu.edu;

import java.util.*;

public class BestGroup {

    public static String findBestGroupUsingHashMap(Map<String, List<Student>> groupIndex) {
        String bestGroup = null;
        double bestAvg = Double.NEGATIVE_INFINITY;

        for (Map.Entry<String, List<Student>> e : groupIndex.entrySet()) {
            List<Student> list = e.getValue();
            if (list == null || list.isEmpty())
                continue;
            double sum = 0;
            for (Student s : list)
                sum += s.getRating();
            double avg = sum / list.size();
            if (avg > bestAvg) {
                bestAvg = avg;
                bestGroup = e.getKey();
            }
        }
        return bestGroup;
    }

    public static String findBestGroupUsingQueue(Map<String, List<Student>> groupIndex) {
        class GroupAvg {
            final String group;
            final double avg;

            GroupAvg(String g, double a) {
                group = g;
                avg = a;
            }
        }

        PriorityQueue<GroupAvg> pq = new PriorityQueue<>(
                Comparator.comparingDouble((GroupAvg ga) -> ga.avg).reversed());

        for (Map.Entry<String, List<Student>> e : groupIndex.entrySet()) {
            List<Student> list = e.getValue();
            if (list == null || list.isEmpty())
                continue;
            double sum = 0;
            for (Student s : list)
                sum += s.getRating();
            double avg = sum / list.size();
            pq.add(new GroupAvg(e.getKey(), avg));
        }

        GroupAvg best = pq.peek();
        return best == null ? null : best.group;
    }

    public static String findBestGroupUsingSet(Map<String, List<Student>> groupIndex) {
        class GroupAvg {
            final String group;
            final double avg;

            GroupAvg(String g, double a) {
                group = g;
                avg = a;
            }
        }

        Comparator<GroupAvg> cmp = Comparator.comparingDouble((GroupAvg ga) -> ga.avg).reversed()
                .thenComparing(ga -> ga.group);

        TreeSet<GroupAvg> set = new TreeSet<>(cmp);

        for (Map.Entry<String, List<Student>> e : groupIndex.entrySet()) {
            List<Student> list = e.getValue();
            if (list == null || list.isEmpty())
                continue;
            double sum = 0;
            for (Student s : list)
                sum += s.getRating();
            double avg = sum / list.size();
            set.add(new GroupAvg(e.getKey(), avg));
        }

        GroupAvg best = set.isEmpty() ? null : set.first();
        return best == null ? null : best.group;
    }

    public static String findBestGroup(Map<String, List<Student>> groupIndex) {
        return findBestGroupUsingHashMap(groupIndex);
    }
}
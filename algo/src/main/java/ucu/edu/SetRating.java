package ucu.edu;

import java.util.*;

public class SetRating {

    public static boolean setRatingUsingHashMap(Map<String, Student> emailIndex, String email, float newRating) {
        Student s = emailIndex.get(email);
        if (s == null)
            return false;
        s.setRating(newRating);
        return true;
    }

    public static boolean setRatingUsingQueue(Queue<Student> queue, String email, float newRating) {
        boolean found = false;
        for (Student s : queue) {
            if (s.getEmail().equals(email)) {
                s.setRating(newRating);
                found = true;
                break;
            }
        }
        return found;
    }

    public static boolean setRatingUsingSet(Set<Student> set, String email, float newRating) {
        Student found = null;
        for (Student s : set) {
            if (s.getEmail().equals(email)) {
                found = s;
                break;
            }
        }
        if (found == null)
            return false;

        boolean removed = set.remove(found);
        found.setRating(newRating);
        set.add(found);
        return removed;
    }

    public static boolean setRating(Map<String, Student> emailIndex, String email, float newRating) {
        return setRatingUsingHashMap(emailIndex, email, newRating);
    }
}

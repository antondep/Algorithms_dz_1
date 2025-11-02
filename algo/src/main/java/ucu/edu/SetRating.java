package ucu.edu;

import java.util.Map;

public class SetRating {
    public static boolean setRating(Map<String, Student> emailIndex, String email, float newRating) {
        Student s = emailIndex.get(email);
        if (s != null) {
            s.setRating(newRating);
            return true;
        }
        return false;
    }
}
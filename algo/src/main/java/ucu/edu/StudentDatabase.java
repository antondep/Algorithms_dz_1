package ucu.edu;

import java.io.*;
import java.util.*;

/**
 * StudentDatabase: load students from CSV and provide multiple container views.
 *
 * Expected CSV format (header + rows) with 9 columns:
 * name,surname,email,birth_year,birth_month,birth_day,group,rating,phone_number
 */
public class StudentDatabase {
    private final List<Student> students = new ArrayList<>();
    private final Map<String, Student> emailIndex = new HashMap<>();
    private final Map<String, List<Student>> groupIndex = new HashMap<>();

    /**
     * Convenience constructor that loads immediately from CSV.
     */
    public StudentDatabase(String csvPath) throws IOException {
        loadFromCSV(csvPath);
    }

    /**
     * Default constructor (empty database).
     */
    public StudentDatabase() {
    }

    /**
     * Load students from CSV file. The method expects a header line, which it
     * skips.
     * Each data row must contain exactly 9 comma-separated fields:
     * name,surname,email,birth_year,birth_month,birth_day,group,rating,phone_number
     */
    public void loadFromCSV(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            // Read and ignore header (if present)
            String header = br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length != 9)
                    continue;

                String name = p[0].trim();
                String surname = p[1].trim();
                String email = p[2].trim();
                int birthYear = Integer.parseInt(p[3].trim());
                int birthMonth = Integer.parseInt(p[4].trim());
                int birthDay = Integer.parseInt(p[5].trim());
                String group = p[6].trim();
                float rating = Float.parseFloat(p[7].trim());
                String phone = p[8].trim();

                // Match Student constructor: (String, String, String, int, int, int, String,
                // float, String)
                Student s = new Student(name, surname, email,
                        birthYear, birthMonth, birthDay,
                        group, rating, phone);

                students.add(s);
                emailIndex.put(email, s);
                groupIndex.computeIfAbsent(group, k -> new ArrayList<>()).add(s);
            }
        }
    }

    /* -- Container accessors used by experiments -- */

    /** Primary list view (modifiable). */
    public List<Student> getStudentsList() {
        return students;
    }

    /** Alias for existing API (keeps backward compatibility). */
    public List<Student> getStudents() {
        return students;
    }

    /** Return a fresh Queue view (LinkedList) */
    public Queue<Student> getStudentsQueue() {
        return new LinkedList<>(students);
    }

    /** Return a fresh Set view (HashSet) */
    public Set<Student> getStudentsSet() {
        return new HashSet<>(students);
    }

    /** Email lookup map */
    public Map<String, Student> getEmailIndex() {
        return emailIndex;
    }

    /** Group index map */
    public Map<String, List<Student>> getGroupIndex() {
        return groupIndex;
    }
    
    public static List<Student> loadSample(String filename, int limit) throws IOException {
        List<Student> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            br.readLine(); // skip header
            String line;
            while ((line = br.readLine()) != null && list.size() < limit) {
                String[] p = line.split(",");
                if (p.length != 9)
                    continue;
                String name = p[0].trim();
                String surname = p[1].trim();
                String email = p[2].trim();
                int birthYear = Integer.parseInt(p[3].trim());
                int birthMonth = Integer.parseInt(p[4].trim());
                int birthDay = Integer.parseInt(p[5].trim());
                String group = p[6].trim();
                float rating = Float.parseFloat(p[7].trim());
                String phone = p[8].trim();

                Student s = new Student(name, surname, email,
                        birthYear, birthMonth, birthDay,
                        group, rating, phone);

                list.add(s);
            }
        }
        return list;
    }
}
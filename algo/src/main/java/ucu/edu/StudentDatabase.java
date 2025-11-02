package ucu.edu;

import java.io.*;
import java.util.*;

public class StudentDatabase {
    private List<Student> students = new ArrayList<>();
    private Map<String, Student> emailIndex = new HashMap<>();
    private Map<String, List<Student>> groupIndex = new HashMap<>();

    public void loadFromCSV(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String header = br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length != 9)
                    continue;
                Student s = new Student(
                        p[0], p[1], p[2],
                        Integer.parseInt(p[3]),
                        Integer.parseInt(p[4]),
                        Integer.parseInt(p[5]),
                        p[6],
                        Float.parseFloat(p[7]),
                        p[8]);
                students.add(s);
                emailIndex.put(s.getEmail(), s);
                groupIndex.computeIfAbsent(s.getGroup(), k -> new ArrayList<>()).add(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Student> getStudents() {
        return students;
    }

    public Map<String, Student> getEmailIndex() {
        return emailIndex;
    }

    public Map<String, List<Student>> getGroupIndex() {
        return groupIndex;
    }
}
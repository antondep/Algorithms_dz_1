package ucu.edu;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class SortBySurnameName {
 
    public static void sortAndSave(List<Student> students, String outputFile) {
        students.sort(Comparator
                .comparing(Student::getSurname)
                .thenComparing(Student::getName));

        saveToCSV(students, outputFile);
    }

    public static void customSortAndSave(List<Student> students, String outputFile) {
        mergeSort(students, 0, students.size() - 1);
        saveToCSV(students, outputFile);
    }

    private static void mergeSort(List<Student> list, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(list, left, mid);
            mergeSort(list, mid + 1, right);
            merge(list, left, mid, right);
        }
    }

    private static void merge(List<Student> list, int left, int mid, int right) {
        List<Student> leftList = list.subList(left, mid + 1);
        List<Student> rightList = list.subList(mid + 1, right + 1);

        int i = 0, j = 0, k = left;
        while (i < leftList.size() && j < rightList.size()) {
            Student s1 = leftList.get(i);
            Student s2 = rightList.get(j);
            int cmp = s1.getSurname().compareTo(s2.getSurname());
            if (cmp == 0)
                cmp = s1.getName().compareTo(s2.getName());

            if (cmp <= 0) {
                list.set(k++, s1);
                i++;
            } else {
                list.set(k++, s2);
                j++;
            }
        }

        while (i < leftList.size()) {
            list.set(k++, leftList.get(i++));
        }
        while (j < rightList.size()) {
            list.set(k++, rightList.get(j++));
        }
    }

    private static void saveToCSV(List<Student> students, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(
                    "m_name,m_surname,m_email,m_birth_year,m_birth_month,m_birth_day,m_group,m_rating,m_phone_number\n");
            for (Student s : students) {
                writer.write(String.format("%s,%s,%s,%d,%d,%d,%s,%.2f,%s\n",
                        s.getName(),
                        s.getSurname(),
                        s.getEmail(),
                        s.getBirthYear(),
                        s.getBirthMonth(),
                        s.getBirthDay(),
                        s.getGroup(),
                        s.getRating(),
                        s.getPhoneNumber()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

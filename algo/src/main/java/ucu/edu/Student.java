package ucu.edu;

public class Student {
    private String m_name;
    private String m_surname;
    private String m_email;
    private int m_birth_year;
    private int m_birth_month;
    private int m_birth_day;
    private String m_group;
    private float m_rating;
    private String m_phone_number;

    public Student(String m_name, String m_surname, String m_email,
            int m_birth_year, int m_birth_month, int m_birth_day,
            String m_group, float m_rating, String m_phone_number) {
        this.m_name = m_name;
        this.m_surname = m_surname;
        this.m_email = m_email;
        this.m_birth_year = m_birth_year;
        this.m_birth_month = m_birth_month;
        this.m_birth_day = m_birth_day;
        this.m_group = m_group;
        this.m_rating = m_rating;
        this.m_phone_number = m_phone_number;
    }

    public String getEmail() {
        return m_email;
    }

    public String getGroup() {
        return m_group;
    }

    public float getRating() {
        return m_rating;
    }

    public void setRating(float rating) {
        this.m_rating = rating;
    }

    public String getName() {
        return m_name;
    }

    public String getSurname() {
        return m_surname;
    }
    
    public int getBirthYear() {
        return m_birth_year;
    }

    public int getBirthMonth() {
        return m_birth_month;
    }

    public int getBirthDay() {
        return m_birth_day;
    }

    public String getPhoneNumber() {
        return m_phone_number;
    }

    @Override
    public String toString() {
        return String.format("%s %s (%.2f, %s)", m_surname, m_name, m_rating, m_group);
    }
}
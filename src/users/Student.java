package users;

import enums.Gender;

public class Student extends User {
    private String major;
    private int yearOfStudy;
    private double gpa;
    private int credits;

    public Student() {}

    public Student(String id, String username, String password,
                   String firstName, String lastName, Gender gender,
                   String major, int yearOfStudy, double gpa, int credits) {
        super(id, username, password, firstName, lastName, gender);
        this.major = major;
        this.yearOfStudy = yearOfStudy;
        this.gpa = gpa;
        this.credits = credits;
    }

    public boolean canRegister(int newCredits) {
        return credits + newCredits <= 21;
    }

    //getters

    public String getMajor() {
        return major;
    }

    public int getYearOfStudy() {
        return yearOfStudy;
    }

    public double getGpa() {
        return gpa;
    }

    public int getCredits() {
        return credits;
    }




    @Override
    public String toString() {
        return "Student{" +
                "id='" + getId() + '\'' +
                ", name='" + getFullName() + '\'' +
                ", major='" + major + '\'' +
                ", yearOfStudy=" + yearOfStudy +
                ", gpa=" + gpa +
                ", credits=" + credits +
                '}';
    }
    
}
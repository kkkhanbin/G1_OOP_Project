package users;

import enums.Gender;
import enums.TeacherTitle;

public class Teacher extends Employee {
    private TeacherTitle title;

    public Teacher() {}

    public Teacher(String id, String username, String password,
                   String firstName, String lastName, Gender gender,
                   double salary, String department, TeacherTitle title) {
        super(id, username, password, firstName, lastName, gender, salary, department);
        this.title = title;
    }

    public TeacherTitle getTitle() {
        return title;
    }

    public void setTitle(TeacherTitle title) {
        this.title = title;
    }

    public boolean isProfessor() {
        return title == TeacherTitle.PROFESSOR;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id='" + getId() + '\'' +
                ", name='" + getFullName() + '\'' +
                ", department='" + getDepartment() + '\'' +
                ", title=" + title +
                '}';
    }
}
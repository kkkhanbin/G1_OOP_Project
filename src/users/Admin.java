package users;

import enums.Gender;
import enums.SchoolType;

public class Admin extends Employee {

    public Admin() {}

    public Admin(String id, String username, String password,
                 String firstName, String lastName, Gender gender,
                 double salary, String department, SchoolType school) {
        super(id, username, password, firstName, lastName, gender, salary, department, school);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id='" + getId() + '\'' +
                ", name='" + getFullName() + '\'' +
                ", department='" + getDepartment() + '\'' +
                ", school=" + getSchool() +
                '}';
    }
}
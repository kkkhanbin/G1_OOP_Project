package users;

import enums.Gender;

public class Admin extends Employee {

    public Admin() {}

    public Admin(String id, String username, String password,
                 String firstName, String lastName, Gender gender,
                 double salary, String department) {
        super(id, username, password, firstName, lastName, gender, salary, department);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "name='" + getFullName() + '\'' +
                ", department='" + getDepartment() + '\'' +
                '}';
    }
}
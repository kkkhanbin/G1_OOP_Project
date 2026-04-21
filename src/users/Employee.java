package users;

import enums.Gender;
import enums.SchoolType;

public class Employee extends User {
    private double salary;
    private String department;
    private SchoolType school;

    public Employee() {}

    public Employee(String id, String username, String password,
                    String firstName, String lastName, Gender gender,
                    double salary, String department, SchoolType school) {
        super(id, username, password, firstName, lastName, gender);
        this.salary = salary;
        this.department = department;
        this.school = school;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public SchoolType getSchool() {
        return school;
    }

    public void setSchool(SchoolType school) {
        this.school = school;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + getId() + '\'' +
                ", name='" + getFullName() + '\'' +
                ", salary=" + salary +
                ", department='" + department + '\'' +
                ", school=" + school +
                '}';
    }
}
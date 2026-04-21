package users;

import enums.Gender;

public class Employee extends User {
    private double salary;
    private String department;

    public Employee() {}

    public Employee(String id, String username, String password,
                    String firstName, String lastName, Gender gender,
                    double salary, String department) {
        super(id, username, password, firstName, lastName, gender);
        this.salary = salary;
        this.department = department;
    }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + getFullName() + '\'' +
                ", salary=" + salary +
                '}';
    }
}
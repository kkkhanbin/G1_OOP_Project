package users;

import enums.Gender;
import enums.ManagerType;
import enums.SchoolType;

public class Manager extends Employee {
    private ManagerType managerType;

    public Manager() {}

    public Manager(String id, String username, String password,
                   String firstName, String lastName, Gender gender,
                   double salary, String department, SchoolType school, ManagerType managerType) {
        super(id, username, password, firstName, lastName, gender, salary, department, school);
        this.managerType = managerType;
    }

    public ManagerType getManagerType() {
        return managerType;
    }

    public void setManagerType(ManagerType managerType) {
        this.managerType = managerType;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id='" + getId() + '\'' +
                ", name='" + getFullName() + '\'' +
                ", department='" + getDepartment() + '\'' +
                ", school=" + getSchool() +
                ", managerType=" + managerType +
                '}';
    }
}
package users;

import enums.Gender;
import enums.ManagerType;

public class Manager extends Employee {
    private ManagerType managerType;

    public Manager() {}

    public Manager(String id, String username, String password,
                   String firstName, String lastName, Gender gender,
                   double salary, String department, ManagerType managerType) {
        super(id, username, password, firstName, lastName, gender, salary, department);
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
                "name='" + getFullName() + '\'' +
                ", department='" + getDepartment() + '\'' +
                ", managerType=" + managerType +
                '}';
    }
}
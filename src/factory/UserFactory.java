package factory;

import enums.Gender;
import enums.ManagerType;
import enums.SchoolType;
import enums.TeacherTitle;
import users.*;

public class UserFactory {

    public static User createUser(String type,
                                  String id,
                                  String username,
                                  String password,
                                  String firstName,
                                  String lastName,
                                  Gender gender) {

        switch (type.toLowerCase()) {
            case "student":
                return new Student(id, username, password, firstName, lastName, gender,
                        "CS", 1, 0.0, 0);

            case "teacher":
                return new Teacher(id, username, password, firstName, lastName, gender,
                        500000, "FIT", SchoolType.FIT, TeacherTitle.LECTURER);

            case "manager":
                return new Manager(id, username, password, firstName, lastName, gender,
                        400000, "Office", SchoolType.FIT, ManagerType.OR);

            case "admin":
                return new Admin(id, username, password, firstName, lastName, gender,
                        450000, "Admin", SchoolType.FIT);

            default:
                throw new IllegalArgumentException("Unknown user type");
        }
    }
}
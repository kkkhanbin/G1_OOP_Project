import users.*;
import enums.*;
import comparators.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Student s1 = new Student("S1", "nurzhan", "123",
                "Nurzhan", "Kalmurat", Gender.MALE,
                "CS", 2, 3.5, 18);

        Student s2 = new Student("S2", "aliya", "123",
                "Aliya", "Serik", Gender.FEMALE,
                "IS", 1, 3.9, 15);

        List<Student> students = new ArrayList<>();
        students.add(s1);
        students.add(s2);

        // сортировка по GPA
        students.sort(new StudentGpaComparator());

        for (Student s : students) {
            System.out.println(s);
        }
    }
}
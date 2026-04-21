package comparators;

import java.util.Comparator;
import users.Student;

public class StudentGpaComparator implements Comparator<Student> {

    @Override
    public int compare(Student s1, Student s2) {
        return Double.compare(s2.getGpa(), s1.getGpa()); // по убыванию GPA
    }
}
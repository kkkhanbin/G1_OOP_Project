package reports;

import courses.Course;
import courses.Mark;
import users.Student;

import java.util.Map;

public class ReportGenerator {

    public static void generateCourseReport(Course course) {
        System.out.println("===== COURSE REPORT =====");
        System.out.println("Course: " + course.getName());

        if (course.getStudentMarks().isEmpty()) {
            System.out.println("No marks available.");
            return;
        }

        double total = 0;
        int passed = 0;
        int failed = 0;
        double max = -1;
        double min = 101;

        for (Map.Entry<Student, Mark> entry : course.getStudentMarks().entrySet()) {
            double score = entry.getValue().getTotal();
            total += score;

            if (entry.getValue().isPassed()) {
                passed++;
            } else {
                failed++;
            }

            if (score > max) max = score;
            if (score < min) min = score;
        }

        double average = total / course.getStudentMarks().size();

        System.out.println("Average score: " + average);
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
        System.out.println("Highest score: " + max);
        System.out.println("Lowest score: " + min);
    }
}
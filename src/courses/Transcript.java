package courses;

import java.util.HashMap;
import java.util.Map;

public class Transcript implements java.io.Serializable {
    private Map<Course, Mark> courseMarks;

    public Transcript() {
        this.courseMarks = new HashMap<>();
    }

    public void addCourseMark(Course course, Mark mark) {
        courseMarks.put(course, mark);
    }

    public double calculateGPA() {
        if (courseMarks.isEmpty()) return 0;

        double totalPoints = 0;
        int totalCourses = 0;

        for (Mark mark : courseMarks.values()) {
            totalPoints += mark.getTotal();
            totalCourses++;
        }

        return (totalPoints / totalCourses) / 25.0;
    }

    public void printTranscript() {
        System.out.println("Transcript:");
        for (Map.Entry<Course, Mark> entry : courseMarks.entrySet()) {
            System.out.println(entry.getKey().getName() + " -> " + entry.getValue());
        }
        System.out.println("GPA: " + calculateGPA());
    }

    @Override
    public String toString() {
        return "Transcript{" +
                "courses=" + courseMarks.size() +
                ", GPA=" + calculateGPA() +
                '}';
    }
}
package courses;

public class Mark implements java.io.Serializable {
    private double att1;
    private double att2;
    private double finalExam;

    public Mark(double att1, double att2, double finalExam) {
        this.att1 = att1;
        this.att2 = att2;
        this.finalExam = finalExam;
    }

    public double getAtt1() {
        return att1;
    }

    public double getAtt2() {
        return att2;
    }

    public double getFinalExam() {
        return finalExam;
    }

    public double getTotal() {
        return att1 + att2 + finalExam;
    }

    public boolean isPassed() {
        return getTotal() >= 50;
    }

    public String getLetterGrade() {
        double total = getTotal();

        if (total >= 95) return "A";
        if (total >= 90) return "A-";
        if (total >= 85) return "B+";
        if (total >= 80) return "B";
        if (total >= 75) return "B-";
        if (total >= 70) return "C+";
        if (total >= 65) return "C";
        if (total >= 60) return "C-";
        if (total >= 55) return "D+";
        if (total >= 50) return "D";
        return "F";
    }

    @Override
    public String toString() {
        return "Mark{" +
                "att1=" + att1 +
                ", att2=" + att2 +
                ", finalExam=" + finalExam +
                ", total=" + getTotal() +
                ", letter='" + getLetterGrade() + '\'' +
                '}';
    }
}
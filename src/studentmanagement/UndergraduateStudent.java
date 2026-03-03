package studentmanagement;

import java.time.LocalDate;

public class UndergraduateStudent extends Student implements ScholarshipEligible {

    private int currentYear; // 学年
    private String club;     // 部活

    public UndergraduateStudent(String id, String name, LocalDate birthDate, String email,
                                String major, int enrollmentYear, int currentYear, String club) {
        super(id, name, birthDate, email, major, enrollmentYear);
        this.currentYear = currentYear;
        this.club = club;
    }

    @Override
    public String getStudentType() {
        return "学部生";
    }

    @Override
    public int getRequiredCredits() {
        return 124; // 例：卒業必要単位
    }

    @Override
    public boolean canGraduate() {
        // ここは簡易：合格科目の単位合計が必要単位以上ならOK（あとで本格化できる）
        int earned = 0;
        for (Grade g : grades) {
            if (g.isPassed()) {
                earned += g.getCredits();
            }
        }
        return earned >= getRequiredCredits();
    }

    @Override
    public String introduce() {
        return String.format("%d年の%sです。専攻は%sで、%sに所属しています。",
                currentYear, name, major, club);
    }

    @Override
    public String generateReport() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== 学部生 成績レポート ===\n");
        sb.append("学生ID: ").append(id).append("\n");
        sb.append("氏名: ").append(name).append("\n");
        sb.append("学年: ").append(currentYear).append("年生\n");
        sb.append("専攻: ").append(major).append("\n");
        sb.append(String.format("GPA: %.2f\n", gpa));
        sb.append("成績レベル: ").append(getGradeLevel()).append("\n");
        sb.append("卒業可能: ").append(canGraduate() ? "可能" : "不可").append("\n");

        if (isEligibleForScholarship()) {
            sb.append("奨学金: ").append(getScholarshipType())
              .append(" (").append(getScholarshipAmount()).append("万円)\n");
        } else {
            sb.append("奨学金: 対象外\n");
        }

        return sb.toString();
    }

    @Override
    public boolean isEligibleForScholarship() {
        return calculateGPA() >= 3.5;
    }

    @Override
    public double getScholarshipAmount() {
        double gpa = calculateGPA();
        if (gpa >= 3.6) return 50.0;   
        return 30.0;                
    }

    @Override
    public String getScholarshipType() {
        double gpa = calculateGPA();
        if (gpa >= 3.6) return "成績優秀奨学金";
        return "一般奨学金";
    }
}
package studentmanagement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public abstract class Student extends Person implements Evaluable, Reportable {

    protected String major;
    protected int enrollmentYear;

    @Builder.Default
    protected List<Grade> grades = new ArrayList<>();

    @Builder.Default
    protected double gpa = 0.0;

    // Studentの種類（学部生/大学院生）
    public abstract String getStudentType();

    // 卒業要件など（学生種別で変わるもの）
    public abstract int getRequiredCredits();
    public abstract boolean canGraduate();

    // 成績追加（例外安全）
    public void addGrade(Grade grade) throws InvalidGradeException {
        if (grade == null) {
            throw new InvalidGradeException("Grade cannot be null");
        }
        if (grade.getScore() < 0 || grade.getScore() > 100) {
            throw new InvalidGradeException("Score must be between 0 and 100: " + grade.getScore());
        }

        grades.add(grade);
        recalculateGPA();
        log.info("Grade added for {}: {} ({}点)", name, grade.getSubject(), grade.getScore());
    }

    // GPA再計算（内部処理）
    private void recalculateGPA() {
        if (grades.isEmpty()) {
            this.gpa = 0.0;
            return;
        }

        double total = 0.0;
        for (Grade g : grades) {
            total += g.getGpaPoint();
        }
        this.gpa = total / grades.size();
    }

    // Evaluable
    @Override
    public double calculateGPA() {
        return this.gpa;
    }

    @Override
    public String getGradeLevel() {
        if (gpa >= 3.8) return "最優秀";
        if (gpa >= 3.5) return "優秀";
        if (gpa >= 3.0) return "良好";
        if (gpa >= 2.5) return "普通";
        return "要努力";
    }

    @Override
    public boolean isEligibleForHonors() {
        return gpa >= 3.8 && grades.size() >= 8;
    }

    // Reportable
    @Override
    public String generateSummary() {
        return String.format("%s（%s）- GPA: %.2f, 成績レベル: %s",
                name, getStudentType(), gpa, getGradeLevel());
    }

    public List<Grade> getGrades() {
        return Collections.unmodifiableList(new ArrayList<>(grades));
    }

    // --- Week6追加分 ---
    public String getDetailedInfo() {
        StringBuilder info = new StringBuilder();
        info.append("=== 学生詳細情報 ===\n");
        info.append("学生ID: ").append(id).append("\n");
        info.append("氏名: ").append(name).append("\n");
        info.append("年齢: ").append(getAge()).append("歳\n");
        info.append("メールアドレス: ").append(email).append("\n");
        info.append("専攻: ").append(major).append("\n");
        info.append("学生タイプ: ").append(getStudentType()).append("\n");
        info.append("====================");
        return info.toString();
    }

    public String getAgeCategory() {
        int age = getAge();
        if (age < 20) return "10代";
        if (age < 25) return "20代前半";
        if (age < 30) return "20代後半";
        return "30代以上";
    }

    public String getSearchableInfo() {
        return String.format("%s %s %s %d %s",
                name, id, major, getAge(), getStudentType());
    }

    public boolean containsKeyword(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return false;
        }
        String lowerKeyword = keyword.toLowerCase();
        String searchableInfo = getSearchableInfo().toLowerCase();
        return searchableInfo.contains(lowerKeyword);
    }
}
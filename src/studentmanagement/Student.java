package studentmanagement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@EqualsAndHashCode(callSuper = true)
public abstract class Student extends Person implements Evaluable, Reportable {

    protected String major;
    protected int enrollmentYear;
    protected final List<Grade> grades;
    protected double gpa;

    public Student(String id, String name, LocalDate birthDate, String email,
                   String major, int enrollmentYear) {
        super(id, name, birthDate, email);
        this.major = major;
        this.enrollmentYear = enrollmentYear;
        this.grades = new ArrayList<>();
        this.gpa = 0.0;
        //logger.info("Student created: " + name);
    }


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

   
    public abstract String getStudentType();

    public abstract int getRequiredCredits();
    public abstract boolean canGraduate();

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

    @Override
    public String generateSummary() {
        return String.format("%s（%s）- GPA: %.2f, 成績レベル: %s",
                name, getStudentType(), gpa, getGradeLevel());
    }
    public List<Grade> getGrades() {
        return Collections.unmodifiableList(new ArrayList<>(grades));
    }
    /**
     * 学生の詳細情報を文字列で返すメソッド
     * Week 6レッスン4で追加：変更管理の実践用
     */
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

    /**
     * 学生の年齢区分を判定するメソッド
     * Week 6レッスン4で追加：Git変更追跡の体験用
     */
    public String getAgeCategory() {
        int age = getAge();
        if (age < 20) {
            return "10代";
        } else if (age < 25) {
            return "20代前半";
        } else if (age < 30) {
            return "20代後半";
        } else {
            return "30代以上";
        }
    }
}
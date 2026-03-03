package studentmanagement;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Grade {
    private String subject;
    private int score;
    private String semester;
    private LocalDate testDate;
    private int credits;

    public double getGpaPoint() {
        double point = (score / 100.0) * 4.0;
        if (point < 0.0) point = 0.0;
        if (point > 4.0) point = 4.0;
        return Math.round(point * 100.0) / 100.0;
    }

    public String getLetterGrade() {
        if (score >= 90) return "A";
        if (score >= 80) return "B";
        if (score >= 70) return "C";
        if (score >= 60) return "D";
        return "F";
    }

    public boolean isPassed() {
        return score >= 60;
    }
}
package studentmanagement;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class GraduateStudent extends Student implements ScholarshipEligible {

    private String degree;       // 修士 / 博士
    private String advisor;      // 指導教員
    private String researchArea; // 研究分野
    private boolean isTA;        // TA担当か

    

    @Override
    public String getStudentType() {
        return "大学院生(" + degree + "課程)";
    }

    @Override
    public int getRequiredCredits() {
        // 例：修士 30単位、博士 20単位 みたいに仮置き
        if ("博士".equals(degree)) return 20;
        return 30; // 修士
    }

    @Override
    public boolean canGraduate() {
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
        return String.format("%s課程の%sです。%sを専攻し、%s先生のもとで%sの研究をしています。%s",
                degree, name, major, advisor, researchArea, (isTA ? "TAも担当しています。" : ""));
    }

    @Override
    public String generateReport() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== 大学院生 成績レポート ===\n");
        sb.append("学生ID: ").append(id).append("\n");
        sb.append("氏名: ").append(name).append("\n");
        sb.append("課程: ").append(degree).append("課程\n");
        sb.append("専攻: ").append(major).append("\n");
        sb.append("指導教員: ").append(advisor).append("\n");
        sb.append("研究分野: ").append(researchArea).append("\n");
        sb.append(String.format("GPA: %.2f\n", gpa));
        sb.append("成績レベル: ").append(getGradeLevel()).append("\n");
        sb.append("卒業可能: ").append(canGraduate() ? "可能" : "不可").append("\n");
        sb.append("TA担当: ").append(isTA ? "あり" : "なし").append("\n");

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
       return true;
    }

    @Override
    public double getScholarshipAmount() {
        if ("博士".equals(degree)) return 30.0; 
        return isTA ? 80.0 : 60.0; 
        }

    @Override
    public String getScholarshipType() {
        if ("博士".equals(degree)) return "博士課程支援金";
        return isTA ? "研究奨励金 + TA奨学金" : "研究奨励金";
    }
    public String getDegree() {
        return degree;
    }
}
package studentmanagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AdvancedStudentManagementSystem {
	
	private final Map<String, Student> students = new LinkedHashMap<>();
	private final Map<String, List<Grade>> gradeHistory = new HashMap<>();

	public AdvancedStudentManagementSystem() {
	    log.info("Advanced Student Management System initialized");
	}

    public void addStudent(Student student) throws StudentManagementException {
        if (student == null) {
            throw new StudentManagementException("Student cannot be null");
        }
        if (students.containsKey(student.getId())) {
            throw new StudentManagementException("Student already exists: " + student.getId());
        }
        students.put(student.getId(), student);
        gradeHistory.put(student.getId(), new ArrayList<>());
        if (student instanceof GraduateStudent) {
            GraduateStudent gs = (GraduateStudent) student;
          
            String degree = gs.getDegree() + "課程";
            log.info("GraduateStudent created: {} ({})", student.getName(), degree);
        } else {
        	log.info("Student created: {} ({})", student.getName(), student.getStudentType());
        }

        log.info("Student registered: {} ({})", student.getName(), student.getStudentType());
    }

    public Student findStudent(String studentId) throws StudentNotFoundException {
        Student student = students.get(studentId);
        if (student == null) {
            throw new StudentNotFoundException(studentId);
        }
        return student;
    }

    public void addGrade(String studentId, Grade grade)
            throws StudentNotFoundException, InvalidGradeException {

        Student student = findStudent(studentId);
        student.addGrade(grade);

        gradeHistory.get(studentId).add(grade);
    }

    public void generateAllReports() {
    	log.info("Generating reports for all students");
        System.out.println("=== 全学生レポート ===");

        students.values().stream()
                .sorted((a, b) -> sortKey(a.getId()).compareTo(sortKey(b.getId())))
                .forEach(student -> {
                    System.out.println(student.generateReport());
                    System.out.println("-".repeat(50));
                });
    }

    private String sortKey(String id) {
        char prefix = id.charAt(0);
        String rest = id.substring(1);

        if (prefix == 'U') return "0" + rest;
        return "1" + rest;
    }

    public void generateStatisticsByType() {
    	log.info("Generating statistics by student type");
        Map<String, List<Student>> byType = new HashMap<>();

        for (Student s : students.values()) {
            String type = s.getStudentType();
            byType.putIfAbsent(type, new ArrayList<>());
            byType.get(type).add(s);
        }

        System.out.println("=== 学生タイプ別統計 ===");
        for (Map.Entry<String, List<Student>> entry : byType.entrySet()) {
            String type = entry.getKey();
            List<Student> list = entry.getValue();

            System.out.println("【" + type + "】");
            System.out.println("学生数: " + list.size() + "名");

            double total = 0.0;
            for (Student s : list) {
                total += s.calculateGPA();
            }
            double avg = list.isEmpty() ? 0.0 : total / list.size();
            System.out.printf("平均GPA: %.2f%n", avg);

            int honors = 0;
            for (Student s : list) {
                if (s.isEligibleForHonors()) honors++;
            }
            System.out.println("優等生数: " + honors + "名");

            int scholarship = 0;
            for (Student s : list) {
                if (s instanceof ScholarshipEligible) {
                    ScholarshipEligible e = (ScholarshipEligible) s;
                    if (e.isEligibleForScholarship()) scholarship++;
                }
            }
            System.out.println("奨学金対象者: " + scholarship + "名");
            System.out.println();
        }
    }

    public void simulateScholarships() throws ScholarshipNotEligibleException {
    	log.info("Running scholarship simulation");
        System.out.println("=== 奨学金シミュレーション ===");

        double totalAmount = 0.0;
        int eligibleCount = 0;

        List<Student> ordered = new ArrayList<>(students.values());
        ordered.sort((a, b) -> {
            String ka = (a.getId().startsWith("U") ? "0" : "1") + a.getId().substring(1);
            String kb = (b.getId().startsWith("U") ? "0" : "1") + b.getId().substring(1);
            return ka.compareTo(kb);
        });
        
        for (Student s : ordered) {
            if (s instanceof ScholarshipEligible) {
                ScholarshipEligible e = (ScholarshipEligible) s;
                if (e.isEligibleForScholarship()) {
                    double amount = e.getScholarshipAmount();
                    String type = e.getScholarshipType();

                    System.out.println(s.getName() + ": " + type + " (" + amount + "万円)");
                    totalAmount += amount;
                    eligibleCount++;
                }
            }
        }

        if (eligibleCount == 0) {
            throw new ScholarshipNotEligibleException("No students eligible for scholarship");
        }

        System.out.println("総支給額: " + totalAmount + "万円");
        System.out.println("対象者数: " + eligibleCount + "名");
    }

    public void showSystemStatus() {
    	log.info("Showing system status");
        System.out.println("=== システム状態 ===");
        System.out.println("登録学生数: " + students.size() + "名");

        int undergraduates = 0;
        int graduates = 0;

        for (Student s : students.values()) {
            if (s instanceof UndergraduateStudent) undergraduates++;
            if (s instanceof GraduateStudent) graduates++;
        }

        System.out.println("- 学部生: " + undergraduates + "名");
        System.out.println("- 大学院生: " + graduates + "名");

        double total = 0.0;
        for (Student s : students.values()) {
            total += s.calculateGPA();
        }
        double avg = students.isEmpty() ? 0.0 : total / students.size();
        System.out.printf("全体平均GPA: %.2f%n", avg);
    }
    
    public Student removeStudent(String studentId) throws StudentNotFoundException {
        Student student = findStudent(studentId);
        students.remove(studentId);
        gradeHistory.remove(studentId);
        log.info("Student removed: {} ({})", student.getName(), student.getStudentType());
        return student;
    }
}
package studentmanagement;

import java.time.LocalDate;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class StudentManagementMain {

    private static void setupLogging() {
        Logger root = LogManager.getLogManager().getLogger("");
        root.setLevel(Level.INFO);

        for (Handler h : root.getHandlers()) {
            root.removeHandler(h);
        }

       
        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(Level.INFO);
        handler.setFormatter(new Formatter() {
            @Override
            public String format(LogRecord r) {
                return "[INFO] " + r.getMessage() + System.lineSeparator();
            }
        });

        root.addHandler(handler);
    }


    public static void main(String[] args) {
    	setupLogging();
        try {
            AdvancedStudentManagementSystem system = new AdvancedStudentManagementSystem();

            System.out.println("=== OOP応用版学生管理システム起動 ===");
            System.out.println("Week 5技術統合デモンストレーション");
            System.out.println();

            UndergraduateStudent tanaka =
            	    UndergraduateStudent.builder()
            	        .id("U001")
            	        .name("田中太郎")
            	        .birthDate(LocalDate.of(2003, 4, 15))
            	        .email("tanaka@university.ac.jp")
            	        .major("コンピュータサイエンス")
            	        .enrollmentYear(2022)
            	        .currentYear(2)
            	        .club("プログラミング研究会")
            	        .build();
            UndergraduateStudent sato =
            	    UndergraduateStudent.builder()
            	        .id("U002")
            	        .name("佐藤花子")
            	        .birthDate(LocalDate.of(2002, 7, 22))
            	        .email("sato@university.ac.jp")
            	        .major("数学")
            	        .enrollmentYear(2021)
            	        .currentYear(3)
            	        .club("数学研究会")
            	        .build();

            GraduateStudent yamada =
            	    GraduateStudent.builder()
            	        .id("G001")
            	        .name("山田次郎")
            	        .birthDate(LocalDate.of(1999, 12, 3))
            	        .email("yamada@graduate.university.ac.jp")
            	        .major("人工知能")
            	        .enrollmentYear(2023)
            	        .degree("修士")
            	        .advisor("AI教授")
            	        .researchArea("機械学習")
            	        .isTA(true)
            	        .build();

            GraduateStudent suzuki =
            	    GraduateStudent.builder()
            	        .id("G002")
            	        .name("鈴木三郎")
            	        .birthDate(LocalDate.of(1998, 8, 10))
            	        .email("suzuki@graduate.university.ac.jp")
            	        .major("量子情報学")
            	        .enrollmentYear(2022)
            	        .degree("博士")
            	        .advisor("量子教授")
            	        .researchArea("量子コンピューティング")
            	        .isTA(false)
            	        .build();

            System.out.println("--- 学生登録処理 ---");
            system.addStudent(tanaka);
            system.addStudent(sato);
            system.addStudent(yamada);
            system.addStudent(suzuki);

            System.out.println("\n--- 成績データ追加 ---");

            system.addGrade("U001", new Grade(
                    "プログラミング基礎", 85, "2023春",
                    LocalDate.of(2023, 7, 15), 3
            ));

            system.addGrade("U001", new Grade(
                    "データ構造", 92, "2023秋",
                    LocalDate.of(2023, 12, 20), 3
            ));

            system.addGrade("U002", new Grade(
                    "微積分学", 95, "2023春",
                    LocalDate.of(2023, 7, 18), 4
            ));

            system.addGrade("U002", new Grade(
                    "線形代数", 88, "2023秋",
                    LocalDate.of(2023, 12, 22), 4
            ));

            system.addGrade("G001", new Grade(
                    "機械学習理論", 93, "2023春",
                    LocalDate.of(2023, 7, 25), 2
            ));

            system.addGrade("G001", new Grade(
                    "深層学習", 96, "2023秋",
                    LocalDate.of(2023, 12, 18), 2
            ));

            system.addGrade("G002", new Grade(
                    "量子力学", 91, "2023春",
                    LocalDate.of(2023, 7, 28), 3
            ));

           
            System.out.println("\n--- 個別自己紹介（ポリモーフィズム） ---");
            tanaka.showDetails();
            System.out.println();
            yamada.showDetails();
            System.out.println();
            
            system.generateAllReports();
            system.generateStatisticsByType();
            system.simulateScholarships();
            System.out.println();
            system.showSystemStatus();

            System.out.println("\n--- 例外処理テスト ---");

            try {
                system.findStudent("X999");
            } catch (StudentNotFoundException e) {
                System.out.println("期待された例外: " + e.getMessage());
            }

            try {
                system.addGrade("U001", new Grade(
                        "テスト科目", 150, "2024春",
                        LocalDate.now(), 3
                ));
            } catch (InvalidGradeException e) {
                System.out.println("期待された例外: " + e.getMessage());
            }

            System.out.println("\n=== システム正常終了 ===");

        } catch (Exception e) {
            System.err.println("システムエラー: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
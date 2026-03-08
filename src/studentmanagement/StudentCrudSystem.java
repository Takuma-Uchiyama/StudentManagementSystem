package studentmanagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentCrudSystem {

    private static final List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            showMenu();
            String choice = scanner.next();

            switch (choice) {
                case "1":
                    showStudents();
                    break;
                case "2":
                    System.out.println("学生追加は未実装です。");
                    break;
                case "3":
                    deleteStudent(scanner);
                    break;
                case "4":
                    System.out.println("GPA検索は未実装です。");
                    break;
                case "5":
                    System.out.println("終了します。");
                    running = false;
                    break;
                default:
                    System.out.println("1〜5を選択してください。");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n=====================");
        System.out.println("    学生管理システム    ");
        System.out.println("=====================");
        System.out.println("1. 👥 学生一覧表示");
        System.out.println("2. ➕ 学生追加");
        System.out.println("3. ➖ 学生削除");
        System.out.println("4. 🔍 GPA検索");
        System.out.println("5. 🚪 終了");
        System.out.print("選択してください (1-5): ");
    }

    private static void showStudents() {
        System.out.println("\n=== 学生一覧 ===");

        if (students.isEmpty()) {
            System.out.println("学生データがありません。");
            return;
        }

        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            System.out.printf("%d. %s / 年齢: %d / GPA: %.2f / 専攻: %s%n",
                    i + 1,
                    student.getName(),
                    student.getAge(),
                    student.getGpa(),
                    student.getMajor());
        }
    }

    private static void deleteStudent(Scanner scanner) {
        if (students.isEmpty()) {
            System.out.println("削除する学生データがありません。");
            return;
        }

        showStudents();
        System.out.print("削除する学生番号を入力してください: ");
        int index = scanner.nextInt() - 1;

        if (index < 0 || index >= students.size()) {
            System.out.println("無効な番号です。");
            return;
        }

        Student target = students.get(index);

        if (confirmDeletion(target.getName())) {
            students.remove(index);
            System.out.println(target.getName() + " を削除しました。");
        } else {
            System.out.println("削除をキャンセルしました。");
        }
    }

    private static boolean confirmDeletion(String studentName) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("本当に " + studentName + " を削除しますか？ (y/n): ");
        String input = scanner.next().toLowerCase();

        return input.equals("y") || input.equals("yes") ||
               input.equals("はい") || input.equals("削除");
    }
}
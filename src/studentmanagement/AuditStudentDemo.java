package studentmanagement;

import java.time.LocalDate;

/**
 * AuditStudentクラスのテストプログラム
 * Week 6レッスン4で新規追加：複数新規ファイル管理の体験用
 */
public class AuditStudentDemo {

    public static void main(String[] args) {
        System.out.println("=== AuditStudent（聴講生）テストプログラム ===");
        System.out.println("Week 6レッスン4：新規ファイル追加の実践\n");

        // 聴講生の作成（birthDateを使用 - Week 5のPersonクラスに合わせた設計）
        AuditStudent student1 = AuditStudent.builder()
                .id("AUD001")
                .name("田中聴子")
                .birthDate(LocalDate.of(1996, 5, 15))
                .email("tanaka@audit.example.com")
                .auditCourse("Java基礎講座")
                .build();
        AuditStudent student2 = AuditStudent.builder()
                .id("AUD002")
                .name("佐藤聴太")
                .birthDate(LocalDate.of(1989, 11, 23))
                .email("sato@audit.example.com")
                .auditCourse("Spring Boot実践講座")
                .build();
        // 基本情報の表示
        System.out.println("1. 聴講生基本情報");
        System.out.println(student1);
        System.out.println(student2);
        System.out.println();

        // 自己紹介（Personの抽象メソッド実装の確認）
        System.out.println("1.5 自己紹介（ポリモーフィズム）");
        System.out.println(student1.introduce());
        System.out.println(student2.introduce());
        System.out.println();

        // 出席のシミュレーション
        System.out.println("2. 出席状況のシミュレーション");
        for (int i = 0; i < 8; i++) {
            student1.markAttendance();
        }

        for (int i = 0; i < 12; i++) {
            student2.markAttendance();
        }
        System.out.println();

        // レポート生成
        System.out.println("3. 聴講生レポート");
        System.out.println(student1.generateAuditReport());
        System.out.println();
        System.out.println(student2.generateAuditReport());
        System.out.println();

        // 聴講終了
        System.out.println("4. 聴講終了処理");
        student1.endAudit();
        student1.markAttendance();  // 終了後の出席試行

        System.out.println("\n=== テスト完了 ===");
    }
}

package studentmanagement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 学生検索機能のデモンストレーション
 * Week 6レッスン4実践演習で作成：検索機能のテスト用
 */
public class SearchDemo {

    public static void main(String[] args) {
        System.out.println("=== 学生検索システム デモンストレーション ===");
        System.out.println("Week 6レッスン4実践演習: 開発フロー体験\n");

        // サンプル学生データの作成（UndergraduateStudentを使用）
        List<Student> students = createSampleStudents();

        System.out.println("1. 全学生データ");
        displayAllStudents(students);

        // 各種検索のデモンストレーション
        demoNameSearch(students);
        demoMajorSearch(students);
        demoAgeRangeSearch(students);
        demoKeywordSearch(students);

        System.out.println("\n=== デモンストレーション完了 ===");
    }

    /**
     * サンプル学生データの作成（UndergraduateStudentのBuilderパターンを使用）
     */
    private static List<Student> createSampleStudents() {
        List<Student> students = new ArrayList<>();

        // Week 5で学んだBuilderパターンを活用
        students.add(UndergraduateStudent.builder()
            .id("STU001")
            .name("田中太郎")
            .birthDate(LocalDate.of(2002, 4, 15))
            .email("tanaka@example.com")
            .major("情報工学")
            .enrollmentYear(2021)
            .currentYear(3)
            .club("プログラミング研究会")
            .build());

        students.add(UndergraduateStudent.builder()
            .id("STU002")
            .name("佐藤花子")
            .birthDate(LocalDate.of(2003, 7, 22))
            .email("sato@example.com")
            .major("コンピュータサイエンス")
            .enrollmentYear(2022)
            .currentYear(2)
            .club("数学研究会")
            .build());

        students.add(UndergraduateStudent.builder()
            .id("STU003")
            .name("山田一郎")
            .birthDate(LocalDate.of(2001, 12, 3))
            .email("yamada@example.com")
            .major("情報工学")
            .enrollmentYear(2020)
            .currentYear(4)
            .club(null)
            .build());

        students.add(UndergraduateStudent.builder()
            .id("STU004")
            .name("鈴木美咲")
            .birthDate(LocalDate.of(2004, 3, 18))
            .email("suzuki@example.com")
            .major("ソフトウェア工学")
            .enrollmentYear(2023)
            .currentYear(1)
            .club("テニス部")
            .build());

        students.add(UndergraduateStudent.builder()
            .id("STU005")
            .name("高橋健太")
            .birthDate(LocalDate.of(2000, 8, 30))
            .email("takahashi@example.com")
            .major("コンピュータサイエンス")
            .enrollmentYear(2019)
            .currentYear(4)
            .club("サッカー部")
            .build());

        return students;
    }

    /**
     * 全学生の表示
     */
    private static void displayAllStudents(List<Student> students) {
        System.out.println(StudentUtils.generateStudentList(students));
        System.out.println();
    }

    /**
     * 名前検索のデモ
     */
    private static void demoNameSearch(List<Student> students) {
        System.out.println("2. 名前検索デモ（キーワード: '田'）");
        List<Student> results = StudentSearcher.searchByName(students, "田");
        System.out.println(StudentSearcher.formatSearchResults(results, "名前検索", "田"));
        System.out.println();
    }

    /**
     * 専攻検索のデモ
     */
    private static void demoMajorSearch(List<Student> students) {
        System.out.println("3. 専攻検索デモ（キーワード: 'コンピュータ'）");
        List<Student> results = StudentSearcher.searchByMajor(students, "コンピュータ");
        System.out.println(StudentSearcher.formatSearchResults(results, "専攻検索", "コンピュータ"));
        System.out.println();
    }

    /**
     * 年齢範囲検索のデモ
     */
    private static void demoAgeRangeSearch(List<Student> students) {
        System.out.println("4. 年齢範囲検索デモ（20歳〜22歳）");
        List<Student> results = StudentSearcher.searchByAgeRange(students, 20, 22);
        System.out.println(StudentSearcher.formatSearchResults(results, "年齢範囲検索", "20-22歳"));
        System.out.println();
    }

    /**
     * キーワード検索のデモ
     */
    private static void demoKeywordSearch(List<Student> students) {
        System.out.println("5. キーワード検索デモ（新機能使用）");
        List<Student> keywordResults = new ArrayList<>();

        for (Student student : students) {
            if (student.containsKeyword("工学")) {
                keywordResults.add(student);
            }
        }

        System.out.println(StudentSearcher.formatSearchResults(keywordResults, "キーワード検索", "工学"));
        System.out.println();
    }
}

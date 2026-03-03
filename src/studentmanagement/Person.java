package studentmanagement;

import java.time.LocalDate;
import java.time.Period;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public abstract class Person {

    protected String id;
    protected String name;
    protected LocalDate birthDate;
    protected String email;

    public Person(String id, String name, LocalDate birthDate, String email) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.email = email;
    }

    public int getAge() {
        LocalDate baseDate = LocalDate.of(2024, 4, 16);
        return Period.between(birthDate, baseDate).getYears();
    }

    public abstract String introduce();

    public final void showDetails() {
        System.out.println("=== 詳細情報 ===");
        System.out.println("ID: " + id);
        System.out.println("名前: " + name);
        System.out.println("年齢: " + getAge() + "歳");
        System.out.println("Email: " + email);
        System.out.println(introduce());
    }
}
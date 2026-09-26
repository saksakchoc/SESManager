package com.aiko.ses.model;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParticipationTest {
    @Test
    void getId_コンストラクタで登録したIDを返す() {
        Engineer engineer = new Engineer(1,"あいこ", 45, "Java");
        Project project = new Project(1,"A銀行基幹システム更改",40,60, LocalDate.parse("2026-05-01"),LocalDate.parse("2027-03-31"));
        Participation p = new Participation(10, engineer, project, LocalDate.parse("2026-05-01"), LocalDate.parse("2027-03-31"), 45);
        assertEquals(10, p.getId());
    }
    @Test
    void getEngineer_コンストラクタで登録したEngineerを返す() {
        Engineer engineer = new Engineer(1,"あいこ", 45, "Java");
        Project project = new Project(1,"A銀行基幹システム更改",40,60, LocalDate.parse("2026-05-01"),LocalDate.parse("2027-03-31"));
        Participation p = new Participation(10, engineer, project, LocalDate.parse("2026-05-01"), LocalDate.parse("2027-03-31"), 45);
        assertEquals(engineer, p.getEngineer());
    }
    @Test
    void getProject_コンストラクタで登録したProjectを返す() {
        Engineer engineer = new Engineer(1, "あいこ", 45, "Java");
        Project project = new Project(1,"A銀行基幹システム更改",40,60, LocalDate.parse("2026-05-01"),LocalDate.parse("2027-03-31"));
        Participation p = new Participation(10, engineer, project, LocalDate.parse("2026-05-01"), LocalDate.parse("2027-03-31"), 45);
        assertEquals(project, p.getProject());
    }
    @Test
    void getStartDate_コンストラクタで登録した開始日を返す() {
        Engineer engineer = new Engineer(1,"あいこ", 45, "Java");
        Project project = new Project(1,"A銀行基幹システム更改",40,60, LocalDate.parse("2026-05-01"),LocalDate.parse("2027-03-31"));
        Participation p = new Participation(10, engineer, project, LocalDate.parse("2026-05-01"), LocalDate.parse("2027-03-31"), 45);
        assertEquals(LocalDate.parse("2026-05-01"), p.getStartDate());
    }
    @Test
    void getEndDate_コンストラクタで登録した終了日を返す() {
        Engineer engineer = new Engineer(1,"あいこ", 45, "Java");
        Project project = new Project(1,"A銀行基幹システム更改",40,60, LocalDate.parse("2026-05-01"),LocalDate.parse("2027-03-31"));
        Participation p = new Participation(10, engineer, project, LocalDate.parse("2026-05-01"), LocalDate.parse("2027-03-31"), 45);
        assertEquals(LocalDate.parse("2027-03-31"), p.getEndDate());
    }
    @Test
    void getContractPrice_コンストラクタで登録した契約単価を返す() {
        Engineer engineer = new Engineer(1,"あいこ", 45, "Java");
        Project project = new Project(1,"A銀行基幹システム更改",40,60, LocalDate.parse("2026-05-01"),LocalDate.parse("2027-03-31"));
        Participation p = new Participation(10, engineer, project, LocalDate.parse("2026-05-01"), LocalDate.parse("2027-03-31"), 45);
        assertEquals(45, p.getContractPrice());
    }
}

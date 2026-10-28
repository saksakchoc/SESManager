package com.aiko.ses.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AssignServiceTest {
    @Test
    void getEngineerParticipation_参画案件一覧が取得できていること() {
        EngineerStore es = new EngineerStore();
        Engineer e = es.createEngineer("あいこ1", 45, "Java");
        ProjectStore pjs = new ProjectStore();
        Project project1 = pjs.createProject("A社基幹システム改修", 40, 60, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"));
        Project project2 = pjs.createProject("B社基幹システム改修", 40, 60, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"));

        ParticipationStore pps = new ParticipationStore();
        pps.createParticipation(e, project1, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"), 45);
        pps.createParticipation(e, project2, LocalDate.parse("2027-04-01"), LocalDate.parse("2028-03-31"), 50);

        AssignService as = new AssignService(pps);

        assertEquals(2, as.getEngineerParticipation(e).size());
        assertEquals(project1, as.getEngineerParticipation(e).get(0).getProject());
        assertEquals(project2, as.getEngineerParticipation(e).get(1).getProject());
    }

    @Test
    void isAvailableByPeriod_対象期間が参画開始日前日まで() {
        EngineerStore es = new EngineerStore();
        Engineer e = es.createEngineer("あいこ1", 45, "Java");
        ProjectStore pjs = new ProjectStore();
        Project project1 = pjs.createProject("A社基幹システム改修", 40, 60, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"));

        ParticipationStore pps = new ParticipationStore();
        pps.createParticipation(e, project1, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"), 45);

        AssignService as = new AssignService(pps);
        assertTrue(as.isAvailableByPeriod(e, LocalDate.parse("2025-04-01"),LocalDate.parse("2026-03-31")));
    }

    @Test
    void isAvailableByPeriod_対象期間が他案件の参画開始日からその参画終了日までの間() {
        EngineerStore es = new EngineerStore();
        Engineer e = es.createEngineer("あいこ1", 45, "Java");
        ProjectStore pjs = new ProjectStore();
        Project project1 = pjs.createProject("A社基幹システム改修", 40, 60, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"));

        ParticipationStore pps = new ParticipationStore();
        pps.createParticipation(e, project1, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"), 45);

        AssignService as = new AssignService(pps);
        assertFalse(as.isAvailableByPeriod(e, LocalDate.parse("2026-04-01"), LocalDate.parse("2026-04-30")));
    }
    @Test
    void isAvailableByPeriod_対象期間が他案件の参画開始日からその参画終了日より後() {
        EngineerStore es = new EngineerStore();
        Engineer e = es.createEngineer("あいこ1", 45, "Java");
        ProjectStore pjs = new ProjectStore();
        Project project1 = pjs.createProject("A社基幹システム改修", 40, 60, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"));

        ParticipationStore pps = new ParticipationStore();
        pps.createParticipation(e, project1, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"), 45);

        AssignService as = new AssignService(pps);
        assertFalse(as.isAvailableByPeriod(e, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-04-30")));
    }

    @Test
    void isAvailableByPeriod_対象期間が参画終了日から() {
        EngineerStore es = new EngineerStore();
        Engineer e = es.createEngineer("あいこ1", 45, "Java");
        ProjectStore pjs = new ProjectStore();
        Project project1 = pjs.createProject("A社基幹システム改修", 40, 60, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"));

        ParticipationStore pps = new ParticipationStore();
        pps.createParticipation(e, project1, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"), 45);

        AssignService as = new AssignService(pps);
        assertFalse(as.isAvailableByPeriod(e, LocalDate.parse("2027-03-31"), LocalDate.parse("2027-04-30")));
    }
    @Test
    void isAvailableByPeriod_対象期間が参画終了日翌日から() {
        EngineerStore es = new EngineerStore();
        Engineer e = es.createEngineer("あいこ1", 45, "Java");
        ProjectStore pjs = new ProjectStore();
        Project project1 = pjs.createProject("A社基幹システム改修", 40, 60, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"));

        ParticipationStore pps = new ParticipationStore();
        pps.createParticipation(e, project1, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"), 45);

        AssignService as = new AssignService(pps);
        assertTrue(as.isAvailableByPeriod(e, LocalDate.parse("2027-04-01"), LocalDate.parse("2028-03-31")));
    }
    @Test
    void isAvailableByPeriod_案件2件のうち2件目に参画中() {
        EngineerStore es = new EngineerStore();
        Engineer e = es.createEngineer("あいこ1", 45, "Java");
        ProjectStore pjs = new ProjectStore();
        Project project1 = pjs.createProject("A社基幹システム改修", 40, 60, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"));
        Project project2 = pjs.createProject("B社基幹システム改修", 40, 60, LocalDate.parse("2027-04-01"), LocalDate.parse("2028-03-31"));

        ParticipationStore pps = new ParticipationStore();
        pps.createParticipation(e, project1, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"), 45);
        pps.createParticipation(e, project2, LocalDate.parse("2027-04-01"), LocalDate.parse("2028-03-31"), 50);

        AssignService as = new AssignService(pps);
        assertFalse(as.isAvailableByPeriod(e, LocalDate.parse("2027-04-01"),LocalDate.parse("2027-04-02")));

    }
    @Test
    void isAvailableByPeriod_案件2件あり対象日は参画なし() {
        EngineerStore es = new EngineerStore();
        Engineer e = es.createEngineer("あいこ1", 45, "Java");
        ProjectStore pjs = new ProjectStore();
        Project project1 = pjs.createProject("A社基幹システム改修", 40, 60, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"));
        Project project2 = pjs.createProject("B社基幹システム改修", 40, 60, LocalDate.parse("2027-04-01"), LocalDate.parse("2028-03-31"));

        ParticipationStore pps = new ParticipationStore();
        pps.createParticipation(e, project1, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"), 45);
        pps.createParticipation(e, project2, LocalDate.parse("2028-04-01"), LocalDate.parse("2029-03-31"), 50);

        AssignService as = new AssignService(pps);
        assertTrue(as.isAvailableByPeriod(e, LocalDate.parse("2027-04-01"), LocalDate.parse("2028-03-31")));
    }
    @Test
    void isAvailableByPrice_単価上下限以内() {
        EngineerStore es = new EngineerStore();
        Engineer e = es.createEngineer("あいこ1", 45, "Java");

        ParticipationStore pps = new ParticipationStore();

        AssignService as = new AssignService(pps);
        assertTrue(as.isAvailableByPrice(e, 45,50));
    }
    @Test
    void isAvailableByPrice_単価上下限より小さい() {
        EngineerStore es = new EngineerStore();
        Engineer e = es.createEngineer("あいこ1", 45, "Java");

        ParticipationStore pps = new ParticipationStore();

        AssignService as = new AssignService(pps);
        assertFalse(as.isAvailableByPrice(e, 46,50));
    }

    @Test
    void isAvailableByPrice_単価上下限より大きい() {
        EngineerStore es = new EngineerStore();
        Engineer e = es.createEngineer("あいこ1", 45, "Java");
        ParticipationStore pps = new ParticipationStore();

        AssignService as = new AssignService(pps);
        assertFalse(as.isAvailableByPrice(e, 41,44));
    }
    @Test
    void isAvailableBySkills_スキルすべて持っている_要求数1つ() {
        EngineerStore es = new EngineerStore();
        Engineer e = es.createEngineer("あいこ1", 45, "Java");

        ParticipationStore pps = new ParticipationStore();

        AssignService as = new AssignService(pps);

        List<String> skills = List.of("Java");

        assertTrue(as.isAvailableBySkills(e, skills));
    }
    @Test
    void isAvailableBySkills_スキルすべて持っている_要求数2つ() {
        EngineerStore es = new EngineerStore();
        Engineer e = es.createEngineer("あいこ1", 45, "Java","SQL","JavaScript");

        ParticipationStore pps = new ParticipationStore();

        AssignService as = new AssignService(pps);

        List<String> skills = List.of("Java", "JavaScript");

        assertTrue(as.isAvailableBySkills(e, skills));
    }
    @Test
    void isAvailableBySkills_スキル1つは持っているが1つ不足_要求数2つ() {
        EngineerStore es = new EngineerStore();
        Engineer e = es.createEngineer("あいこ1", 45, "Java","SQL","JavaScript");

        ParticipationStore pps = new ParticipationStore();

        AssignService as = new AssignService(pps);

        List<String> skills = List.of("Java", "python");

        assertFalse(as.isAvailableBySkills(e, skills));
    }
    @Test
    void canAssign_参画条件をすべて満たす() {
        EngineerStore es = new EngineerStore();
        Engineer e = es.createEngineer("あいこ1", 45, "Java");
        ProjectStore pjs = new ProjectStore();
        Project project1 = pjs.createProject("A社基幹システム改修", 40, 60, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"));

        ParticipationStore pps = new ParticipationStore();

        AssignService as = new AssignService(pps);
        assertTrue(as.canAssign(e, project1));
    }

    @Test
    void canAssign_期間条件を満たさない() {
        EngineerStore es = new EngineerStore();
        Engineer e = es.createEngineer("あいこ1", 45, "Java");
        ProjectStore pjs = new ProjectStore();
        Project project1 = pjs.createProject("A社基幹システム改修", 40, 60, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"));

        ParticipationStore pps = new ParticipationStore();
        /* 同じ期間の案件に参画中 */
        pps.createParticipation(e, project1, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"), 45);

        AssignService as = new AssignService(pps);
        assertFalse(as.canAssign(e, project1));
    }
    @Test
    void canAssign_価格条件を満たさない() {
        EngineerStore es = new EngineerStore();
        Engineer e = es.createEngineer("あいこ1", 45, "Java");
        ProjectStore pjs = new ProjectStore();
        Project project1 = pjs.createProject("A社基幹システム改修", 50, 60, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"));

        ParticipationStore pps = new ParticipationStore();

        AssignService as = new AssignService(pps);
        assertFalse(as.canAssign(e, project1));
    }

    @Test
    void canAssign_スキル条件を満たさない() {
        EngineerStore es = new EngineerStore();
        Engineer e = es.createEngineer("あいこ1", 45, "Java");
        ProjectStore pjs = new ProjectStore();
        /* pythonが必須 */
        Project project1 = pjs.createProject("A社基幹システム改修", 50, 60, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"),"python");

        ParticipationStore pps = new ParticipationStore();

        AssignService as = new AssignService(pps);
        assertFalse(as.canAssign(e, project1));
    }

    @Test
    void canAssign_プロジェクトが募集中でない() {
        EngineerStore es = new EngineerStore();
        Engineer e = es.createEngineer("あいこ1", 45, "Java");
        ProjectStore pjs = new ProjectStore();
        /* pythonが必須 */
        Project project1 = pjs.createProject("A社基幹システム改修", 50, 60, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"), false);

        ParticipationStore pps = new ParticipationStore();

        AssignService as = new AssignService(pps);
        assertFalse(as.canAssign(e, project1));
    }
}

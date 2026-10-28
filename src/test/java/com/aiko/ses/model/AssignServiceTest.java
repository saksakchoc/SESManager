package com.aiko.ses.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

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
    void isAvailableByDay_対象日付が参画開始日前日() {
        EngineerStore es = new EngineerStore();
        Engineer e = es.createEngineer("あいこ1", 45, "Java");
        ProjectStore pjs = new ProjectStore();
        Project project1 = pjs.createProject("A社基幹システム改修", 40, 60, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"));

        ParticipationStore pps = new ParticipationStore();
        pps.createParticipation(e, project1, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"), 45);

        AssignService as = new AssignService(pps);
        assertTrue(as.isAvailableByDay(e, LocalDate.parse("2026-03-31")));

    }

    @Test
    void isAvailableByDay_対象日付が参画開始日() {
        EngineerStore es = new EngineerStore();
        Engineer e = es.createEngineer("あいこ1", 45, "Java");
        ProjectStore pjs = new ProjectStore();
        Project project1 = pjs.createProject("A社基幹システム改修", 40, 60, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"));

        ParticipationStore pps = new ParticipationStore();
        pps.createParticipation(e, project1, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"), 45);

        AssignService as = new AssignService(pps);
        assertFalse(as.isAvailableByDay(e, LocalDate.parse("2026-04-01")));

    }

    @Test
    void isAvailableByDay_対象日付が参画終了日() {
        EngineerStore es = new EngineerStore();
        Engineer e = es.createEngineer("あいこ1", 45, "Java");
        ProjectStore pjs = new ProjectStore();
        Project project1 = pjs.createProject("A社基幹システム改修", 40, 60, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"));

        ParticipationStore pps = new ParticipationStore();
        pps.createParticipation(e, project1, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"), 45);

        AssignService as = new AssignService(pps);
        assertFalse(as.isAvailableByDay(e, LocalDate.parse("2027-03-31")));

    }
    @Test
    void isAvailableByDay_対象日付が参画終了日翌日() {
        EngineerStore es = new EngineerStore();
        Engineer e = es.createEngineer("あいこ1", 45, "Java");
        ProjectStore pjs = new ProjectStore();
        Project project1 = pjs.createProject("A社基幹システム改修", 40, 60, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"));

        ParticipationStore pps = new ParticipationStore();
        pps.createParticipation(e, project1, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"), 45);

        AssignService as = new AssignService(pps);
        assertTrue(as.isAvailableByDay(e, LocalDate.parse("2027-04-01")));

    }
    @Test
    void isAvailableByDay_案件2件のうち2件目に参画中() {
        EngineerStore es = new EngineerStore();
        Engineer e = es.createEngineer("あいこ1", 45, "Java");
        ProjectStore pjs = new ProjectStore();
        Project project1 = pjs.createProject("A社基幹システム改修", 40, 60, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"));
        Project project2 = pjs.createProject("B社基幹システム改修", 40, 60, LocalDate.parse("2027-04-01"), LocalDate.parse("2028-03-31"));

        ParticipationStore pps = new ParticipationStore();
        pps.createParticipation(e, project1, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"), 45);
        pps.createParticipation(e, project2, LocalDate.parse("2027-04-01"), LocalDate.parse("2028-03-31"), 50);

        AssignService as = new AssignService(pps);
        assertFalse(as.isAvailableByDay(e, LocalDate.parse("2027-04-01")));

    }
    @Test
    void isAvailableByDay_案件2件あり対象日は参画なし() {
        EngineerStore es = new EngineerStore();
        Engineer e = es.createEngineer("あいこ1", 45, "Java");
        ProjectStore pjs = new ProjectStore();
        Project project1 = pjs.createProject("A社基幹システム改修", 40, 60, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"));
        Project project2 = pjs.createProject("B社基幹システム改修", 40, 60, LocalDate.parse("2027-04-01"), LocalDate.parse("2028-03-31"));

        ParticipationStore pps = new ParticipationStore();
        pps.createParticipation(e, project1, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"), 45);
        pps.createParticipation(e, project2, LocalDate.parse("2027-04-01"), LocalDate.parse("2028-03-31"), 50);

        AssignService as = new AssignService(pps);
        assertTrue(as.isAvailableByDay(e, LocalDate.parse("2028-04-01")));

    }
}

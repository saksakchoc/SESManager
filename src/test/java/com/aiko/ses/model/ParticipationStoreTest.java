package com.aiko.ses.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParticipationStoreTest {

    @Test
    void getParticipations_参画情報一覧が取得できる() {
        EngineerStore es = new EngineerStore();
        Engineer engineer = es.createEngineer("あいこ1", 45, "Java");
        ProjectStore pjs = new ProjectStore();
        Project project = pjs.createProject("A社基幹システム改修", 40, 60, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"));
        ;
        ParticipationStore pps = new ParticipationStore();
        Participation p1 = pps.createParticipation(engineer, project, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"), 45);
        Participation p2 = pps.createParticipation(engineer, project, LocalDate.parse("2027-04-01"), LocalDate.parse("2029-03-31"), 50);
        Participation p3 = pps.createParticipation(engineer, project, LocalDate.parse("2028-04-01"), LocalDate.parse("2029-03-31"), 55);
        assertEquals(3,pps.getParticipations().size());
        List<Participation> participations = pps.getParticipations();
        assertEquals(p1, participations.get(0));
        assertEquals(p2, participations.get(1));
        assertEquals(p3, participations.get(2));
    }
    @Test
    void getParticipations_getParticipations_取得した参画情報一覧は変更できないこと() {
        EngineerStore es = new EngineerStore();
        Engineer engineer = es.createEngineer("あいこ1", 45, "Java");
        ProjectStore pjs = new ProjectStore();
        Project project = pjs.createProject("A社基幹システム改修", 40, 60, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"));

        ParticipationStore pps = new ParticipationStore();
        Participation p1 = pps.createParticipation(engineer, project, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"), 45);
        Participation p2 = new Participation(2, engineer, project, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"), 100);
        assertThrows(UnsupportedOperationException.class, () -> {
            pps.getParticipations().add(p2);
        });
        assertThrows(UnsupportedOperationException.class, () -> {
            pps.getParticipations().clear();
        });
        assertThrows(UnsupportedOperationException.class, () -> {
            pps.getParticipations().remove(0);
        });
    }
    @Test
    void createParticipation_ID採番が想定通り() {
        EngineerStore es = new EngineerStore();
        Engineer engineer  = es.createEngineer("あいこ1", 45, "Java");
        ProjectStore pjs = new ProjectStore();
        Project project = pjs.createProject("A社基幹システム改修" , 40, 60, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"));
        ParticipationStore pps = new ParticipationStore();
        pps.createParticipation(engineer, project, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"), 45);
        pps.createParticipation(engineer, project, LocalDate.parse("2027-04-01"), LocalDate.parse("2028-03-31"), 50);
        Participation participation3 = pps.createParticipation(engineer, project, LocalDate.parse("2028-04-01"), LocalDate.parse("2029-03-31"), 55);
        pps.createParticipation(engineer, project, LocalDate.parse("2029-04-01"), LocalDate.parse("2030-03-31"), 60);
        assertEquals(3, participation3.getId());
    }
    @Test
    void createParticipation_参画情報オブジェクトを作成して返す() {
        EngineerStore es = new EngineerStore();
        Engineer engineer  = es.createEngineer("あいこ1", 45, "Java");
        ProjectStore pjs = new ProjectStore();
        Project project = pjs.createProject("A社基幹システム改修" , 40, 60, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"));
        ParticipationStore pps = new ParticipationStore();
        Participation participation = pps.createParticipation(engineer, project, LocalDate.parse("2028-04-01"), LocalDate.parse("2029-03-31"), 55);
        assertEquals(1, participation.getId());
        assertEquals(LocalDate.parse("2028-04-01"), participation.getStartDate());
        assertEquals(LocalDate.parse("2029-03-31"), participation.getEndDate());
        assertEquals(55, participation.getContractPrice());
        assertEquals(engineer, participation.getEngineer());
        assertEquals(project, participation.getProject());
    }
    @Test
    void createParticipation_参画情報オブジェクトが配列に追加される() {
        EngineerStore es = new EngineerStore();
        Engineer engineer  = es.createEngineer("あいこ1", 45, "Java");
        ProjectStore pjs = new ProjectStore();
        Project project = pjs.createProject("A社基幹システム改修" , 40, 60, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"));
        ParticipationStore pps = new ParticipationStore();
        assertEquals(0, pps.countParticipations());
        Participation p = pps.createParticipation(engineer, project, LocalDate.parse("2028-04-01"), LocalDate.parse("2029-03-31"), 55);

        assertEquals(1, pps.countParticipations());
        assertEquals(p, pps.getParticipation(1));
    }
    @Test
    void getParticipation_参画情報オブジェクトが想定通り取得できる() {
        EngineerStore es = new EngineerStore();
        Engineer engineer  = es.createEngineer("あいこ1", 45, "Java");
        ProjectStore pjs = new ProjectStore();
        Project project = pjs.createProject("A社基幹システム改修" , 40, 60, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"));
        ParticipationStore pps = new ParticipationStore();
        Participation participation = pps.createParticipation(engineer, project, LocalDate.parse("2028-04-01"), LocalDate.parse("2029-03-31"), 55);
        assertEquals(participation, pps.getParticipation(1));
    }
    @Test
    void getParticipation_存在しないIDならnullを返す() {
        EngineerStore es = new EngineerStore();
        Engineer engineer  = es.createEngineer("あいこ1", 45, "Java");
        ProjectStore pjs = new ProjectStore();
        Project project = pjs.createProject("A社基幹システム改修" , 40, 60, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"));
        ParticipationStore pps = new ParticipationStore();
        pps.createParticipation(engineer, project, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"), 55);
        assertNull(pps.getParticipation(2));
    }

    @Test
    void countParticipations_参画情報の件数が取得できる() {
        EngineerStore es = new EngineerStore();
        Engineer engineer = es.createEngineer("あいこ1", 45, "Java");
        ProjectStore pjs = new ProjectStore();
        Project project = pjs.createProject("A社基幹システム改修", 40, 60, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"));
        
        ParticipationStore pps = new ParticipationStore();
        pps.createParticipation(engineer, project, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"), 45);
        pps.createParticipation(engineer, project, LocalDate.parse("2027-04-01"), LocalDate.parse("2029-03-31"), 50);
        pps.createParticipation(engineer, project, LocalDate.parse("2028-04-01"), LocalDate.parse("2029-03-31"), 55);
        assertEquals(3, pps.countParticipations());
    }
}

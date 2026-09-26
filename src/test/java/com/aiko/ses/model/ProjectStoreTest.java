package com.aiko.ses.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ProjectStoreTest {

    @Test
    void createProject_ID採番が想定通り() {
        ProjectStore es = new ProjectStore();
        es.createProject("A社基幹システム改修" , 40, 60, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"));
        es.createProject("A社基幹システム改修2" , 40, 60, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"));
        Project project3 = es.createProject("A社基幹システム改修3" , 40, 60, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"));
        es.createProject("A社基幹システム改修3" , 40, 60, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"));
        assertEquals(project3.getId(), 3);
    }
    @Test
    void createProject_案件オブジェクトを作成して返す() {
        ProjectStore es = new ProjectStore();
        Project project1 = new Project(1,"A社基幹システム改修" , 40, 60, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"));
        Project project2 = es.createProject("A社基幹システム改修" , 40, 60, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"));
        assertEquals(project2.getName() ,project1.getName());
    }
    @Test
    void createProject_案件オブジェクトが配列に追加される() {
        ProjectStore es = new ProjectStore();
        Project project1 = es.createProject("A社基幹システム改修" , 40, 60, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"));;
        assertEquals(project1 ,es.getProject(1));
    }
    @Test
    void getProject_案件オブジェクトが想定通り取得できる() {
        ProjectStore es = new ProjectStore();
        Project project = es.createProject("A社基幹システム改修" , 40, 60, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"));;
        assertEquals(es.getProject(1), project);
    }
    @Test
    void getProject_存在しないIDならnullを返す() {
        ProjectStore es = new ProjectStore();
        es.createProject("A社基幹システム改修" , 40, 60, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"));;
        assertNull(es.getProject(2));
    }

    @Test
    void countProjects_案件配列のサイズが取得できる() {
        ProjectStore es = new ProjectStore();
        es.createProject("A社基幹システム改修1" , 40, 60, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"));;
        es.createProject("A社基幹システム改修2" , 40, 60, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"));;
        es.createProject("A社基幹システム改修3" , 40, 60, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"));;
        assertEquals(es.countProjects(), 3);
    }

    @Test
    void createProject_引数が不正なら案件作成エラー() {
        ProjectStore es = new ProjectStore();

        assertThrows(IllegalArgumentException.class, () -> {
            es.createProject("A銀行基幹システム更改",40,100, LocalDate.parse("2026-05-01"),LocalDate.parse("2026-03-31"));
        });
    }

}

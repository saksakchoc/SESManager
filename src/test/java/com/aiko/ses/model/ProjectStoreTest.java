package com.aiko.ses.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProjectStoreTest {

    @Test
    void createProject_ID採番が想定通り() {
        ProjectStore es = new ProjectStore();
        es.createProject("A社基幹システム改修" , 40, 60, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"));
        es.createProject("A社基幹システム改修2" , 40, 60, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"));
        Project project3 = es.createProject("A社基幹システム改修3" , 40, 60, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"));
        es.createProject("A社基幹システム改修3" , 40, 60, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"));
        assertEquals(3, project3.getId());
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
        Project project1 = es.createProject("A社基幹システム改修" , 40, 60, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"));
        assertEquals(project1 ,es.getProject(1));
    }
    @Test
    void getProject_案件オブジェクトが想定通り取得できる() {
        ProjectStore es = new ProjectStore();
        Project project = es.createProject("A社基幹システム改修" , 40, 60, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"));
        assertEquals(es.getProject(1), project);
    }
    @Test
    void getProject_存在しないIDならnullを返す() {
        ProjectStore es = new ProjectStore();
        es.createProject("A社基幹システム改修" , 40, 60, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"));
        assertNull(es.getProject(2));
    }

    @Test
    void countProjects_案件配列のサイズが取得できる() {
        ProjectStore es = new ProjectStore();
        es.createProject("A社基幹システム改修1" , 40, 60, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"));
        es.createProject("A社基幹システム改修2" , 40, 60, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"));
        es.createProject("A社基幹システム改修3" , 40, 60, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"));
        assertEquals(3, es.countProjects());
    }

    @Test
    void createProject_引数が不正なら案件作成エラー() {
        ProjectStore es = new ProjectStore();

        assertThrows(IllegalArgumentException.class, () ->
            es.createProject("A銀行基幹システム更改",40,100, LocalDate.parse("2026-05-01"),LocalDate.parse("2026-03-31"))
        );
    }
    @Test
    void getProjects_案件を全件取得_0件() {
        ProjectStore es = new ProjectStore();
        assertEquals(0, es.getProjects().size());
    }
    @Test
    void getProjects_案件を全件取得_複数件() {
        ProjectStore es = new ProjectStore();
        Project project1 = es.createProject("A社基幹システム改修1" , 40, 60, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"));
        Project project2 = es.createProject("A社基幹システム改修2" , 60, 80, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"));
        Project project3 = es.createProject("A社基幹システム改修3" , 80, 100, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"));
        assertEquals(project1, es.getProjects().get(0));
        assertEquals(project2, es.getProjects().get(1));
        assertEquals(project3, es.getProjects().get(2));
    }
    @Test
    void getProjects_プロジェクトを全件取得_取得したリストは編集不可() {
        ProjectStore es = new ProjectStore();
        es.createProject("A社基幹システム改修1" , 40, 60, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"));

        List<Project> projects = es.getProjects();

        Project project2 = new Project (2, "A社基幹システム改修2" , 60, 80, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"));

        assertThrows(UnsupportedOperationException.class,
                () -> projects.add(project2));

    }
    @Test
    void getProjects_プロジェクトを全件取得_取得後にエンジニアを追加してもリストの状態は保持される() {
        ProjectStore es = new ProjectStore();
        es.createProject("A社基幹システム改修1" , 40, 60, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"));

        List<Project> projects = es.getProjects();

        int prevProjectListSize = projects.size();

        es.createProject("A社基幹システム改修2" , 60, 80, LocalDate.parse("2026-04-01"), LocalDate.parse("2027-03-31"));

        assertEquals(prevProjectListSize, projects.size());
    }
}

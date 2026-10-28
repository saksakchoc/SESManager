package com.aiko.ses.model;
import java.time.LocalDate;
import java.util.ArrayList;

public class ProjectStore {
    private final ArrayList<Project> projects = new ArrayList<>();

    public Project getProject(int id) {
        for (Project project: projects) {
            if(project.getId() == id) {
                return project;
            }
        }
        return null;
    }

    public int countProjects() {
        return projects.size();
    }

    public Project createProject(String name, int minPrice, int maxPrice, LocalDate startDate, LocalDate endDate, String... skills) {
        int id = countProjects() + 1;
        Project project = new Project(id, name, minPrice, maxPrice, startDate, endDate, skills);
        projects.add(project);
        return project;
    }
    public Project createProject(String name, int minPrice, int maxPrice, LocalDate startDate, LocalDate endDate, boolean isRecruiting, String... skills) {
        Project project = this.createProject(name, minPrice, maxPrice, startDate, endDate, skills);
        /* 募集状態がクローズ */
        if (!isRecruiting) {
            project.setRecruitingStatus(Project.RecruitingStatus.CLOSED);
        }
        return project;
    }
}

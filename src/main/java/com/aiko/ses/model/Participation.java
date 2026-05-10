package com.aiko.ses.model;
import java.time.LocalDate;

public class Participation {
    private final int id;
    private final Engineer engineer;
    private final Project project;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final int contractPrice;

    public Participation(int id, Engineer engineer, Project project, LocalDate startDate, LocalDate endDate, int contractPrice) {
        if (engineer == null || project == null) {
            throw new IllegalArgumentException("エンジニアまたは案件が設定されていません");
        }
        if (startDate == null || endDate == null || endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("参画期間が不正です");
        }
        if (contractPrice < 1 || contractPrice > 1000) {
            throw new IllegalArgumentException("契約単価が不正な値です");
        }

        this.id = id;
        this.engineer = engineer;
        this.project = project;
        this.startDate = startDate;
        this.endDate = endDate;
        this.contractPrice = contractPrice;
    }

    public int getId() {
        return this.id;
    }
    public Engineer getEngineer() {
        return this.engineer;
    }
    public Project getProject() {
        return this.project;
    }
    public LocalDate getStartDate() {
        return this.startDate;
    }
    public LocalDate getEndDate() {
        return this.endDate;
    }
    public int getContractPrice() {
        return this.contractPrice;
    }
}

package com.aiko.ses.model;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ParticipationStore {
    private final ArrayList<Participation> participations = new ArrayList<>();

    public List<Participation> getParticipations() {
        return List.copyOf(participations);
    }

    public Participation getParticipation(int id) {
        for (Participation participation: participations) {
            if(participation.getId() == id) {
                return participation;
            }
        }
        return null;
    }

    public int countParticipations() {
        return participations.size();
    }

    public Participation createParticipation(Engineer engineer, Project project, LocalDate startDate, LocalDate endDate, int contractPrice) {
        int id = countParticipations() + 1;
        Participation participation = new Participation(id, engineer, project, startDate, endDate, contractPrice);
        participations.add(participation);
        return participation;
    }
}

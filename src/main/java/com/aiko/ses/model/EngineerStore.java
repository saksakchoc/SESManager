package com.aiko.ses.model;
import java.util.ArrayList;

public class EngineerStore {
    private ArrayList<Engineer> engineers = new ArrayList<>();

    public Engineer getEngineer(int id) {
        for (Engineer engineer: engineers) {
            if(engineer.getId() == id) {
                return engineer;
            }
        }
        return null;
    }

    public int countEngineers() {
        return engineers.size();
    }

    public Engineer createEngineer(String name, int price, String... skills) {
        int id = countEngineers() + 1;
        Engineer engineer = new Engineer(id, name, price, skills);
        engineers.add(engineer);
        return engineer;
    }
}

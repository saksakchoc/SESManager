package com.aiko.ses.model;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EngineerStoreTest {

    @Test
    void createEngineer_ID採番が想定通り() {
        EngineerStore es = new EngineerStore();
        es.createEngineer("あいこ1", 45, "Java");
        es.createEngineer("あいこ2", 45, "Java");
        Engineer engineer3 = es.createEngineer("あいこ3", 45, "Java");
        es.createEngineer("あいこ4", 45, "Java");
        assertEquals(engineer3.getId(), 3);
    }
    @Test
    void createEngineer_エンジニアオブジェクトを作成して返す() {
        EngineerStore es = new EngineerStore();
        Engineer engineer1 = new Engineer(1,"あいこ", 45, "Java");
        Engineer engineer2 = es.createEngineer("あいこ", 45, "Java");
        assertEquals(engineer2.getName() ,engineer1.getName());
    }
    @Test
    void createEngineer_エンジニアオブジェクトが配列に追加される() {
        EngineerStore es = new EngineerStore();
        Engineer engineer1 = es.createEngineer("あいこ", 45, "Java");
        assertEquals(engineer1 ,es.getEngineer(1));
    }
    @Test
    void getEngineer_エンジニアオブジェクトが想定通り取得できる() {
        EngineerStore es = new EngineerStore();
        Engineer engineer = es.createEngineer("あいこ", 45, "Java");
        assertEquals(es.getEngineer(1), engineer);
    }
    @Test
    void getEngineer_存在しないIDならnullを返す() {
        EngineerStore es = new EngineerStore();
        es.createEngineer("あいこ", 45, "Java");
        assertNull(es.getEngineer(2));
    }


    @Test
    void countEngineers_エンジニア配列のサイズが取得できる() {
        EngineerStore es = new EngineerStore();
        es.createEngineer("あいこ", 45, "Java");
        es.createEngineer("あいこ2", 50, "Java, JavaScript");
        es.createEngineer("あいこ3", 100, "Java, JavaScript, AWS");
        assertEquals(es.countEngineers(), 3);
    }


}

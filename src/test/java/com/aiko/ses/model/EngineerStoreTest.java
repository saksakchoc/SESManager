package com.aiko.ses.model;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EngineerStoreTest {

    @Test
    void createEngineer_ID採番が想定通り() {
        EngineerStore es = new EngineerStore();
        es.createEngineer("あいこ1", 45, "Java");
        es.createEngineer("あいこ2", 45, "Java");
        Engineer engineer3 = es.createEngineer("あいこ3", 45, "Java");
        es.createEngineer("あいこ4", 45, "Java");
        assertEquals(3, engineer3.getId());
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
        assertEquals(3, es.countEngineers());
    }
    @Test
    void getEngineers_エンジニアを全件取得_0件() {
        EngineerStore es = new EngineerStore();
        assertEquals(0,es.getEngineers().size());
    }
    @Test
    void getEngineers_エンジニアを全件取得_複数件() {
        EngineerStore es = new EngineerStore();
        Engineer engineer1 = es.createEngineer("あいこ", 45, "Java");
        Engineer engineer2 = es.createEngineer("あいこ2", 50, "Java, JavaScript");
        Engineer engineer3 = es.createEngineer("あいこ3", 100, "Java, JavaScript, AWS");
        assertEquals(engineer1, es.getEngineers().get(0));
        assertEquals(engineer2, es.getEngineers().get(1));
        assertEquals(engineer3, es.getEngineers().get(2));
    }
    @Test
    void getEngineers_エンジニアを全件取得_取得したリストは編集不可() {
        EngineerStore es = new EngineerStore();
        es.createEngineer("あいこ", 45, "Java");

        List<Engineer> engineers = es.getEngineers();

        Engineer engineer2 = new Engineer(2, "あいこ3", 100, "Java, JavaScript, AWS");

        assertThrows(UnsupportedOperationException.class,
                () -> engineers.add(engineer2));

    }
    @Test
    void getEngineers_エンジニアを全件取得_取得後にエンジニアを追加してもリストの状態は保持される() {
        EngineerStore es = new EngineerStore();
        es.createEngineer("あいこ", 45, "Java");

        List<Engineer> engineers = es.getEngineers();

        int prevEngineerListSize = engineers.size();

        es.createEngineer("あいこ2", 50, "");

        assertEquals(prevEngineerListSize, engineers.size());
    }
}

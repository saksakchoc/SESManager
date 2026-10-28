package com.aiko.ses.model;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EngineerTest {

    @Test
    void hasSkill_持っている場合true() {
        Engineer e = new Engineer(1,"あいこ", 45, "Java");
        assertTrue(e.hasSkill("Java"));
    }

    @Test
    void hasSkill_持っていない場合false() {
        Engineer e = new Engineer(1,"あいこ", 45, "Java");
        assertFalse(e.hasSkill("Python"));
    }
    @Test
    void hasSkill_持っている場合true_複数所持者() {
        Engineer e = new Engineer(1,"あいこ", 45, "Java", "React");
        assertTrue(e.hasSkill("Java"));
    }
    @Test
    void getSkills_配列を変更不可() {
        Engineer e = new Engineer(1,"あいこ", 45, "React", "Java");
        List<String> skills = e.getSkills();
        assertThrows(UnsupportedOperationException.class, () -> {
            skills.add("Python");
        });
    }
    @Test
    void getName_コンストラクタで登録した名前を返す() {
        String engineerName = "あいこ";
        Engineer e = new Engineer(1,engineerName, 45, "React", "Java");
        assertEquals(engineerName, e.getName());
    }
    @Test
    void getPrice_コンストラクタで登録した単価を返す() {
        int engineerPrice = 45;
        Engineer e = new Engineer(1,"あいこ", engineerPrice, "React", "Java");
        assertEquals(engineerPrice, e.getPrice());
    }
    @Test
    void getSkills_コンストラクタで登録したスキルセットを返す() {
        Engineer e = new Engineer(1,"あいこ", 45, "React", "Java");
        List<String> skills = e.getSkills();
        assertTrue(skills.contains("React"));
        assertTrue(skills.contains("Java"));
    }
    @Test
    void getSkills_スキルなし_正常終了() {
        Engineer e = new Engineer(1,"あいこ", 45);
        List<String> skills = e.getSkills();
        assertEquals(0, skills.size());
    }
}

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
    @Test
    void canParticipateIn_募集中_単価範囲内_スキルあり_true() {
        Engineer e = new Engineer(1,"あいこ", 45, "React","Java");
        Project p = new Project("A銀行基幹システム更改",40,100, LocalDate.parse("2026-05-01"),LocalDate.parse("2027-03-31"),"Java");
        p.setRecruitingStatus(Project.RecruitingStatus.RECRUITING);
        assertTrue(e.canParticipateIn(p));
    }
    @Test
    void canParticipateIn_募集なし_単価範囲内_スキルあり_false() {
        Engineer e = new Engineer(1,"あいこ", 45, "React","Java");
        Project p = new Project("A銀行基幹システム更改",40,100, LocalDate.parse("2026-05-01"),LocalDate.parse("2027-03-31"),"Java");
        assertFalse(e.canParticipateIn(p));
    }
    @Test
    void canParticipateIn_募集中_単価範囲外_スキルあり_false() {
        Engineer e = new Engineer(1,"あいこ", 200, "React","Java");
        Project p = new Project("A銀行基幹システム更改",40,100, LocalDate.parse("2026-05-01"),LocalDate.parse("2027-03-31"),"Java");
        p.setRecruitingStatus(Project.RecruitingStatus.RECRUITING);
        assertFalse(e.canParticipateIn(p));
    }
    @Test
    void canParticipateIn_募集中_単価範囲内_スキルなし_false() {
        Engineer e = new Engineer(1,"あいこ", 45, "MySQL");
        Project p = new Project("A銀行基幹システム更改",40,100, LocalDate.parse("2026-05-01"),LocalDate.parse("2027-03-31"),"Java");
        p.setRecruitingStatus(Project.RecruitingStatus.RECRUITING);
        assertFalse(e.canParticipateIn(p));
    }
    @Test
    void canParticipateIn_募集中_単価範囲内_スキル一部不足_false() {
        Engineer e = new Engineer(1,"あいこ", 45, "Java","React");
        Project p = new Project("A銀行基幹システム更改",40,100, LocalDate.parse("2026-05-01"),LocalDate.parse("2027-03-31"),"Java","React","MySQL");
        p.setRecruitingStatus(Project.RecruitingStatus.RECRUITING);
        assertFalse(e.canParticipateIn(p));
    }
    @Test
    void canParticipateIn_募集中_単価範囲内_スキル完全一致なし_大文字小文字は別扱い_false() {
        Engineer e = new Engineer(1,"あいこ", 45, "JAVA");
        Project p = new Project("A銀行基幹システム更改",40,100, LocalDate.parse("2026-05-01"),LocalDate.parse("2027-03-31"),"Java");
        p.setRecruitingStatus(Project.RecruitingStatus.RECRUITING);
        assertFalse(e.canParticipateIn(p));
    }
    @Test
    void canParticipateIn_募集中_単価範囲内_スキル完全一致なし_空白ありなしは別扱い_false() {
        Engineer e = new Engineer(1,"あいこ", 45, "Java ");
        Project p = new Project("A銀行基幹システム更改",40,100, LocalDate.parse("2026-05-01"),LocalDate.parse("2027-03-31"),"Java");
        p.setRecruitingStatus(Project.RecruitingStatus.RECRUITING);
        assertFalse(e.canParticipateIn(p));
    }

}

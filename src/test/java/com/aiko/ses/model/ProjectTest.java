package com.aiko.ses.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectTest {
    @Test
    void Project_スキルなし_正常終了_スキル配列はサイズ0() {
        Project p = new Project("A銀行基幹システム更改",40,100, LocalDate.parse("2026-05-01"),LocalDate.parse("2027-03-31"));
        assertEquals(0, p.getSkills().size());
    }

    @Test
    void Project_単価上下限が不正ならエラー() {
        assertThrows(IllegalArgumentException.class, () -> {
            Project p = new Project("A銀行基幹システム更改",101,100, LocalDate.parse("2026-05-01"),LocalDate.parse("2027-03-31"));
        });
    }
    @Test
    void Project_開始終了日が不正ならエラー() {
        assertThrows(IllegalArgumentException.class, () -> {
            Project p = new Project("A銀行基幹システム更改",40,100, LocalDate.parse("2026-05-01"),LocalDate.parse("2026-03-31"));
        });
    }
    @Test
    void getName_コンストラクタで設定した名前を取得できること(){
        Project p = new Project("A銀行基幹システム更改",40,100, LocalDate.parse("2026-05-01"),LocalDate.parse("2027-03-31"));
        assertEquals("A銀行基幹システム更改", p.getName());
    }
    @Test
    void getMinPrice_コンストラクタで設定した単価最低値を取得できること(){
        Project p = new Project("A銀行基幹システム更改",40,100, LocalDate.parse("2026-05-01"),LocalDate.parse("2027-03-31"));
        assertEquals(40, p.getMinPrice());
    }
    @Test
    void getMaxPrice_コンストラクタで設定した単価最高値を取得できること(){
        Project p = new Project("A銀行基幹システム更改",40,100, LocalDate.parse("2026-05-01"),LocalDate.parse("2027-03-31"));
        assertEquals(100, p.getMaxPrice());
    }
    @Test
    void getStartDate_コンストラクタで設定した開始日を取得できること(){
        Project p = new Project("A銀行基幹システム更改",40,60, LocalDate.parse("2026-05-01"),LocalDate.parse("2027-03-31"));
        assertEquals(LocalDate.parse("2026-05-01"), p.getStartDate());
    }
    @Test
    void getEndDate_コンストラクタで設定した終了日を取得できること(){
        Project p = new Project("A銀行基幹システム更改",40,60, LocalDate.parse("2026-05-01"),LocalDate.parse("2027-03-31"));
        assertEquals(LocalDate.parse("2027-03-31"), p.getEndDate());
    }
    @Test
    void getSkills_コンストラクタで登録したスキルセットを返す() {
        Project p = new Project("A銀行基幹システム更改",40,60, LocalDate.parse("2026-05-01"),LocalDate.parse("2027-03-31"), "React", "Java");
        List<String> skills = p.getSkills();
        assertEquals("React", skills.get(0));
        assertEquals("Java", skills.get(1));
    }
    @Test
    void hasSkill_持っている場合true() {
        Project p = new Project("A銀行基幹システム更改",40,60, LocalDate.parse("2026-05-01"),LocalDate.parse("2027-03-31"), "React", "Java");
        assertTrue(p.hasSkill("Java"));
    }
    @Test
    void hasSkill_持っていない場合false() {
        Project p = new Project("A銀行基幹システム更改",40,60, LocalDate.parse("2026-05-01"),LocalDate.parse("2027-03-31"), "React", "Java");
        assertFalse(p.hasSkill("MySQL"));
    }
    @Test
    void getSkills_配列を変更不可() {
        Project p = new Project("A銀行基幹システム更改",40,60, LocalDate.parse("2026-05-01"),LocalDate.parse("2027-03-31"), "React", "Java");
        List<String> skills = p.getSkills();
        assertThrows(UnsupportedOperationException.class, () -> {
            skills.add("Python");
        });
    }
    @Test
    void isRecruiting_募集中区分を取得_デフォルト値はfalse() {
        Project p = new Project("A銀行基幹システム更改",40,60, LocalDate.parse("2026-05-01"),LocalDate.parse("2027-03-31"), "React", "Java");
        assertEquals(false, p.isRecruiting());
    }
    @Test
    void isRecruiting_募集中区分を取得_trueをセット() {
        Project p = new Project("A銀行基幹システム更改",40,60, LocalDate.parse("2026-05-01"),LocalDate.parse("2027-03-31"), "React", "Java");
        p.setRecruitingStatus(Project.RecruitingStatus.RECRUITING);
        assertEquals(true, p.isRecruiting());
    }
    @Test
    void isRecruiting_募集中区分を取得_falseをセット() {
        Project p = new Project("A銀行基幹システム更改",40,60, LocalDate.parse("2026-05-01"),LocalDate.parse("2027-03-31"), "React", "Java");
        p.setRecruitingStatus(Project.RecruitingStatus.CLOSED);
        assertEquals(false, p.isRecruiting());
    }
    @Test
    void isWithinPrice_範囲内ならtrue() {
        Project p = new Project("A銀行基幹システム更改",40,60, LocalDate.parse("2026-05-01"),LocalDate.parse("2027-03-31"), "React", "Java");
        assertTrue(p.isWithinPrice(50));
    }
    @Test
    void isWithinPrice_範囲内ならtrue_下限() {
        Project p = new Project("A銀行基幹システム更改",40,60, LocalDate.parse("2026-05-01"),LocalDate.parse("2027-03-31"), "React", "Java");
        assertTrue(p.isWithinPrice(40));
    }
    @Test
    void isWithinPrice_範囲内ならtrue_上限() {
        Project p = new Project("A銀行基幹システム更改",40,60, LocalDate.parse("2026-05-01"),LocalDate.parse("2027-03-31"), "React", "Java");
        assertTrue(p.isWithinPrice(60));
    }
    @Test
    void isWithinPrice_範囲外ならfalse_下限以下() {
        Project p = new Project("A銀行基幹システム更改",40,60, LocalDate.parse("2026-05-01"),LocalDate.parse("2027-03-31"), "React", "Java");
        assertFalse(p.isWithinPrice(30));
    }
    @Test
    void isWithinPrice_範囲外ならfalse_上限以上() {
        Project p = new Project("A銀行基幹システム更改",40,60, LocalDate.parse("2026-05-01"),LocalDate.parse("2027-03-31"), "React", "Java");
        assertFalse(p.isWithinPrice(120));
    }
}

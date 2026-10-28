package com.aiko.ses.model;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 案件クラス
 * 案件の固有情報を保持
 */
public class Project {
    public enum RecruitingStatus{RECRUITING, CLOSED};
    private final int id;
    private final String name;
    private final List<String> skills = new ArrayList<>();
    private final int minPrice;
    private final int maxPrice;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private RecruitingStatus status = RecruitingStatus.RECRUITING;

    /* コンストラクタ
     * 名前と単価は必須 */
    public Project(int id, String name, int minPrice, int maxPrice, LocalDate startDate, LocalDate endDate, boolean isRecruiting, String... skills) {
        this(id, name, minPrice, maxPrice, startDate, endDate, isRecruiting);
        this.skills.addAll(Arrays.asList(skills));
    }
    public Project(int id, String name, int minPrice, int maxPrice, LocalDate startDate, LocalDate endDate, boolean isRecruiting) {
        if (minPrice > maxPrice) {
            throw new IllegalArgumentException("案件の単価上下限が不正です");
        }
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("案件の開始終了日が不正です");
        }
        this.id = id;
        this.name = name;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.startDate = startDate;
        this.endDate = endDate;
        if (!isRecruiting) {
            this.status = RecruitingStatus.CLOSED;
        }
    }
    public Project(int id, String name, int minPrice, int maxPrice, LocalDate startDate, LocalDate endDate, String... skills) {
        /* 募集中の案件として作成 */
        this(id, name, minPrice, maxPrice, startDate, endDate, true, skills);
    }
    public void setRecruitingStatus(RecruitingStatus rs) {
        this.status = rs;
    }
    public int getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public int getMinPrice() {
        return this.minPrice;
    }
    public int getMaxPrice() {
        return this.maxPrice;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    /* スキルリストを返す
     * ArrayListを隠蔽するため、コピーを渡す */
    public List<String> getSkills() {
        return List.copyOf(skills);
    }
    /* 入力されたスキルを保有するかどうかを判定
     * 保有する場合trueを返す */
    public boolean hasSkill(String skill) {
        for (String s: this.skills) {
            if (s.equals(skill)) {
                return true;
            }
        }
        return false;
    }
    public boolean isRecruiting() {
        return (status == RecruitingStatus.RECRUITING);
    }
    public boolean isWithinPrice(int price) {
        return price >= minPrice && price <= maxPrice;
    }
}

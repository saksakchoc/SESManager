package com.aiko.ses.model;
import java.util.ArrayList;
import java.util.List;

/* エンジニアクラス
 * エンジニアの固有情報を保持
 */
public class Engineer {
    private final int id;
    private final String name;
    private final List<String> skills = new ArrayList<>();
    private final int price;
    /* コンストラクタ
     * 名前と単価は必須 */
    public Engineer(int id, String name, int price, String... skills) {
        this(id, name, price);
        for (String skill: skills) {
            this.skills.add(skill);
        }
    }
    public Engineer(int id, String name, int price) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("エンジニア氏名が空欄です");
        }
        if (price < 1) {
            throw new IllegalArgumentException("目安単価の値が不正です");
        }
        this.id = id;
        this.name = name;
        this.price = price;
    }
    public int getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public int getPrice() {
        return this.price;
    }
    /* スキルリストを返す
     * ArrayListを隠蔽するため、コピーを渡す */
    public List<String> getSkills() {
        return List.copyOf(skills);
    }
}

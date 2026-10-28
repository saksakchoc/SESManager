package com.aiko.ses.model;
import java.time.LocalDate;
import java.util.List;

public class AssignService {
    private ParticipationStore pps;
    public AssignService(ParticipationStore pps) {
        this.pps = pps;
    }

    public List<Participation> getEngineerParticipation(Engineer e) {
        return this.pps.getParticipations().stream()
                .filter(p -> p.getEngineer() == e)
                .toList();
    }
    public boolean isAvailableByPeriod(Engineer e, LocalDate startDate, LocalDate endDate) {
        /* 開始日 <= 終了日でなければエラー */
        if (!endDate.isAfter(startDate)) {
            throw new IllegalArgumentException("判定期間の開始終了日が不正です");
        }
        /* エンジニアが全期間で参画されている案件一覧を取得する */
        List<Participation> participationList = this.getEngineerParticipation(e);
        /* 参画されている案件のうち、対象期間と参画期間が重複する要素を取得し、存在しなければtrueを返す */
        return participationList.stream()
                .filter(p -> !(p.getEndDate().isBefore(startDate) || p.getStartDate().isAfter(endDate)))
                .toList()
                .isEmpty();
    }
    public boolean isAvailableByPrice(Engineer e, int minPrice, int maxPrice) {
        /* エンジニアの単価目安 */
        int price = e.getPrice();
        /* エンジニアの単価目安が、引数の最低単価と最高単価の間であればtrueを返す */
        return (price >= minPrice && price <= maxPrice);
    }
    public boolean isAvailableBySkills(Engineer e, List<String> skills) {
        /* エンジニアのスキル */
        List<String> engineerSkills = e.getSkills();
        /* エンジニアが、引数のスキル一覧をすべて有している場合trueを返す */
        return engineerSkills.containsAll(skills);
    }
    public boolean canAssign(Engineer e, Project p) {
        return isAvailableByPeriod(e, p.getStartDate(), p.getEndDate())
                && isAvailableByPrice(e, p.getMinPrice(), p.getMaxPrice())
                && isAvailableBySkills(e, p.getSkills())
                && p.isRecruiting();
    }
}

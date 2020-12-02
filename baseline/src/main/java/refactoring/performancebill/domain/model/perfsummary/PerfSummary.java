package refactoring.performancebill.domain.model.perfsummary;

import java.util.ArrayList;
import java.util.List;

public class PerfSummary {
    private String customer;
    List<Perf> perfs = new ArrayList<>();

    public PerfSummary() {}

    public PerfSummary(String customer) {
        this.customer = customer;
    }

    public String getCustomer() {
        return customer;
    }

    public List<Perf> getPerfs() {
        return perfs;
    }

    public void addPerformance(String playId, int audience) {
        Perf p = new Perf(playId, audience);
        perfs.add(p);
    }
}

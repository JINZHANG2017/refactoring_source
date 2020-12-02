package refactoring.performancebill.domain.model.perfsummary;

public class Perf {

    private String playId;
    private int audience;

    public Perf() {}

    public Perf(String playId, int audience) {
        this.playId = playId;
        this.audience = audience;
    }

    public String getPlayId() {
        return playId;
    }

    public int getAudience() {
        return audience;
    }
}

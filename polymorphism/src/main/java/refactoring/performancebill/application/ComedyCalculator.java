package refactoring.performancebill.application;

import refactoring.performancebill.domain.model.perfsummary.Perf;
import refactoring.performancebill.domain.model.play.Play;

public class ComedyCalculator extends Calculator {
    @Override
    public int calAmount(Perf perf, Play play) {
        int thisAmount;
        thisAmount = 30000;
        if (perf.getAudience() > 20) {
            thisAmount += 10000 + 500 * (perf.getAudience() - 20);
        }
        thisAmount += 300 * perf.getAudience();
        return thisAmount;
    }

    @Override
    public int calCredits(Perf perf, Play play) {
        //计算观众量积分
        int thisCredits = 0;
        thisCredits = Math.max(perf.getAudience() - 30, 0);
        thisCredits += Math.floorDiv(perf.getAudience(), 5);
        return thisCredits;
    }
}

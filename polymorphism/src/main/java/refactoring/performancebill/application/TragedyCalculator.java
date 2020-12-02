package refactoring.performancebill.application;

import refactoring.performancebill.domain.model.perfsummary.Perf;
import refactoring.performancebill.domain.model.play.Play;

public class TragedyCalculator extends Calculator {
    @Override
    public int calAmount(Perf perf, Play play) {
        int thisAmount;
        thisAmount = 40000;
        if (perf.getAudience() > 30) {
            thisAmount += 1000 * (perf.getAudience() - 30);
        }
        return thisAmount;
    }

    @Override
    public int calCredits(Perf perf, Play play) {
        //计算观众量积分
        int thisCredits = 0;
        thisCredits = Math.max(perf.getAudience() - 30, 0);
        return thisCredits;
    }
}

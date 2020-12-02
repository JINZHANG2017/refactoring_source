package refactoring.performancebill.application;

import refactoring.performancebill.domain.model.perfsummary.Perf;
import refactoring.performancebill.domain.model.play.Play;

public abstract class Calculator {
    public abstract int calAmount(Perf perf, Play play);

    public abstract int calCredits(Perf perf, Play play);
}

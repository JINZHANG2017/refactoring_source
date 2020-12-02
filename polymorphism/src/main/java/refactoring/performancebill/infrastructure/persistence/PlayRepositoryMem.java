package refactoring.performancebill.infrastructure.persistence;

import org.springframework.stereotype.Repository;
import refactoring.performancebill.domain.model.play.Play;
import refactoring.performancebill.domain.model.play.PlayRepository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class PlayRepositoryMem implements PlayRepository {
    @Override
    public Play findById(String playId) {
        Map<String, Play> plays = new HashMap<>();
        plays.put("hamlet", new Play("hamelet", "Hamlet", "tragedy"));
        plays.put("as-like", new Play("as-like", "As You Like It", "comedy"));
        plays.put("othello", new Play("othello", "Othello", "tragedy"));




        return plays.get(playId);
    }
}

package guess_game.service;

import guess_game.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ResultHandling {

    private final Map<Player, Long> score;

    public ResultHandling() {
        score = new HashMap<>();
    }

    public void savePlayerScore(Player player, Long acquiredPoints) {
        score.put(player, acquiredPoints);
    }

    public Map<Player, Long> getScoreBoard() {
        return score.entrySet().stream()
                .collect(Collectors.toUnmodifiableMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}

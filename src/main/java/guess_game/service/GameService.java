package guess_game.service;

import guess_game.Player;
import guess_game.database.ScoreInMemoryStorage;

import java.util.Map;
import java.util.UUID;

public class GameService {

    private ScoreInMemoryStorage<Map.Entry<Player, Long>> scoreStorage;

    public GameService() {
        scoreStorage = new ScoreInMemoryStorage<>();
    }

    public boolean savePlayerScore(Player player, Long points) {
        Map.Entry<Player, Long> entry = Map.entry(player, points);
        return scoreStorage.save(entry);
    }

    public Map<UUID, Map.Entry<Player, Long>> getPlayerScores() {
        return scoreStorage.getScores();
    }
}

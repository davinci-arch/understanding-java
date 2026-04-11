package guess_game;

import java.util.Map;
import java.util.UUID;

public record Player(String nickname, Long globalScore, Map<UUID, Long> gamesHistory) {
}

package guess_game;

import java.time.LocalDate;
import java.util.UUID;

public record GameSessionEntity(UUID idGame, Player player, LocalDate startedAt, LocalDate endedAt, Long wonPoints) {
}

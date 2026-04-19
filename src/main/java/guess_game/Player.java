package guess_game;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class Player implements Serializable {
    private String nickname;
    private Long globalScore;
    private final Map<UUID, GameSessionEntity> gamesHistory = new HashMap<>();

    public Player(String nickname, Long globalScore) {
        this.nickname = nickname;
        this.globalScore = globalScore;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Long getGlobalScore() {
        return globalScore;
    }

    public void updateGameHistory(UUID idGame, GameSessionEntity gameSession) {
        gamesHistory.put(idGame, gameSession);
    }

    public void setGlobalScore(Long globalScore) {
        this.globalScore = globalScore;
    }

    public Map<UUID, GameSessionEntity> getGamesHistory() {
        return Collections.unmodifiableMap(gamesHistory);
    }
}

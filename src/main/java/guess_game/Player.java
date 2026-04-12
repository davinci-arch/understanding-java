package guess_game;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class Player {
    private String nickname;
    private Long globalScore;
    private final Map<UUID, Long> gamesHistory = new HashMap<>();

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

    public void updateGameHistory (UUID idGame, Long score) {
        gamesHistory.put(idGame, score);
    }

    public void setGlobalScore(Long globalScore) {
        this.globalScore = globalScore;
    }

    public Map<UUID, Long> getGamesHistory() {
        return gamesHistory.entrySet().stream()
                .collect(Collectors.toUnmodifiableMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}

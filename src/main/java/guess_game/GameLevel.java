package guess_game;

public enum GameLevel {
    EASY(100L),
    MEDIUM(250L),
    DIFFICULT(1000L),
    IMPOSSIBLE(5000L);

    final Long pointsForWinning;

    GameLevel(Long pointsForWinning) {
        this.pointsForWinning = pointsForWinning;
    }
}

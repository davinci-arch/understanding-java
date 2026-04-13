package guess_game;

public enum GameLevel {
    EASY(100L, 100),
    MEDIUM(250L, 300),
    DIFFICULT(1000L, 1100),
    IMPOSSIBLE(5000L, 5000);

    final Long pointsForWinning;
    final int upperBound;

    GameLevel(Long pointsForWinning, int upperBound) {
        this.pointsForWinning = pointsForWinning;
        this.upperBound = upperBound;
    }
}

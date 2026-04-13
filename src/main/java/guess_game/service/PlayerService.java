package guess_game.service;

import guess_game.GameSessionEntity;
import guess_game.Player;
import guess_game.database.PlayersInMemoryStorage;
import guess_game.exceptions.NoSuchPlayerPresent;

import java.util.*;

public class PlayerService {

    private final PlayersInMemoryStorage<Player> storage;

    public PlayerService() {
        this.storage = new PlayersInMemoryStorage<>();
    }

    public void saveHistory(Player player, GameSessionEntity gameSession) {
        var playerFound = findPlayer(player);
        var idGame = UUID.randomUUID();
        var validObj = playerFound.orElseThrow(() ->
                new NoSuchPlayerPresent(
                        String.format("Player %s was not found", player.toString())
                ));
        validObj.updateGameHistory(idGame, gameSession);
    }

    public void updateAmountOfPoints(Player player, Long points) {
        var playerFound = findPlayer(player);
        playerFound.ifPresent(p -> p.setGlobalScore(points));
    }

    public Map<UUID, GameSessionEntity> getPlayerHistory(Player player) {
        var playerFound = findPlayer(player);
        return playerFound.isPresent() ? playerFound.get().getGamesHistory() : Collections.emptyMap();
    }

    private Optional<Player> findPlayer(Player player) {
        var players = storage.getPlayers();
        return players.stream()
                .filter(i -> i.equals(player))
                .findFirst();
    }
}

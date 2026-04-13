package guess_game.service;

import guess_game.GameSessionEntity;
import guess_game.Player;
import guess_game.database.PlayersInMemoryStorage;
import guess_game.exceptions.NoSuchPlayerPresent;
import guess_game.exceptions.PlayerAlreadyExists;
import guess_game.exceptions.PlayerDoesntExist;

import java.util.*;

public class PlayerService {

    private final PlayersInMemoryStorage<Player> storage;

    public PlayerService() {
        this.storage = new PlayersInMemoryStorage<>();
    }

    public void saveHistory(Player player, GameSessionEntity gameSession) {
        var playerFound = findPlayer(player.getNickname());
        var idGame = UUID.randomUUID();
        var validObj = playerFound.orElseThrow(() ->
                new NoSuchPlayerPresent(
                        String.format("Player %s was not found", player.toString())
                ));
        validObj.updateGameHistory(idGame, gameSession);
    }

    public void updateAmountOfPoints(Player player, Long points) {
        var playerFound = findPlayer(player.getNickname());
        playerFound.ifPresent(p -> p.setGlobalScore(points));
    }

    public Map<UUID, GameSessionEntity> getPlayerHistory(Player player) {
        var playerFound = findPlayer(player.getNickname());
        return playerFound.isPresent() ? playerFound.get().getGamesHistory() : Collections.emptyMap();
    }

    private Optional<Player> findPlayer(String nickname) {
        var players = storage.getPlayers();
        return players.stream()
                .filter(i -> i.getNickname().equals(nickname))
                .findFirst();
    }

    public List<Player> getTopTenPlayers() {
        var players = storage.getPlayers();
        return players.stream()
                .sorted(Comparator.comparing(Player::getGlobalScore).reversed())
                .limit(10)
                .toList();
    }

    public Player registerPlayer(String nickname) {
        if (storage.load(nickname).isPresent()) {
            throw new PlayerAlreadyExists(String.format("Player %s already exists", nickname));
        }
        var newPlayer = new Player(nickname, 0L);
        storage.save(newPlayer);
        return newPlayer;
    }
    public Player authorizePlayer(String nickname) {
        var foundPlayer = findPlayer(nickname);
        return foundPlayer.orElseThrow(() -> new PlayerDoesntExist("Player " + nickname + " was not found"));
    }
}

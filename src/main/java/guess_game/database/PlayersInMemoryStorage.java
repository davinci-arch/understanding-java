package guess_game.database;

import guess_game.Player;

import java.util.*;

public class PlayersInMemoryStorage<T extends Player> implements Storage<T> {

    private final List<T> players = new ArrayList<>();

    @Override
    public boolean save(T t) {
        return players.add(t);
    }

    @Override
    public Optional<T> load(String nickname) {
        return players.stream()
                .filter(p -> p.getNickname().equals(nickname))
                .findFirst();
    }

    @Override
    public Map<UUID, T> loadAll() {
        return Map.of();
    }

    public List<T> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}

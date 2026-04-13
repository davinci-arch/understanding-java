package guess_game.database;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PlayersInMemoryStorage<T> implements Storage<T> {

    private List<T> players;

    @Override
    public boolean save(T t) {
        return players.add(t);
    }

    @Override
    public T load() {
        return null;
    }

    public List<T> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}

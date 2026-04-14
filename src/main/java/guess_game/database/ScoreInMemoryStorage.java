package guess_game.database;

import java.util.*;

public class ScoreInMemoryStorage<T> implements Storage<T> {

    public final Map<UUID, T> scores = new HashMap<>();

    @Override
    public boolean save(T o) {
        return scores.put(UUID.randomUUID(), o) != null;
    }

    @Override
    public Optional<T> load(String matcherLine) {
        return null;
    }

    @Override
    public Map<UUID, T> loadAll() {
        return Collections.unmodifiableMap(scores);
    }
}

package guess_game.database;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class ScoreInMemoryStorage<T> implements Storage<T> {

    public final Map<UUID, T> scores = new HashMap<>();

    @Override
    public boolean save(T o) {
        return scores.put(UUID.randomUUID(), o) != null;
    }

    @Override
    public T load() {
        return null;
    }

    public Map<UUID, T> getScores() {
        return Collections.unmodifiableMap(scores);
    }
}

package guess_game.database;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface Storage<T> {

    boolean save(T t);
    Optional<T> load(String matcherLine);
    Map<UUID, T> loadAll();
}

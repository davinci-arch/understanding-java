package guess_game.database;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface Storage<T> {

    boolean save(T t);
    T load();
    Map<UUID, T> loadAll();
}

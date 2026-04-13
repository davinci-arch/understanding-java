package guess_game.database;

public interface Storage<T> {

    boolean save(T t);

    T load();
}

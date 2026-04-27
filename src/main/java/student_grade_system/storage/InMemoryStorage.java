package student_grade_system.storage;

import java.util.ArrayList;
import java.util.List;
/**
 * In-memory implementation of {@link Storage} using a {@link List}.
 *
 * @param <T> type of stored objects
 */
public class InMemoryStorage<T> implements Storage<T> {

    private List<T> storage = new ArrayList<>();
    /**
     * Saves the object if it is not already present in storage.
     *
     * @param o object to store
     */
    @Override
    public void save(T o) {
        if (!storage.contains(o)) {
            storage.add(o);
        }
    }
    /**
     * Removes and returns the last inserted element.
     *
     * @return last stored element
     */
    @Override
    public T load() {
        return storage.isEmpty() ? null : storage.get(0);
    }
    /**
     * Returns all elements as an unmodifiable list.
     *
     * @return copy of stored elements
     */
    @Override
    public List<T> loadAll() {
        return List.copyOf(storage);
    }
}

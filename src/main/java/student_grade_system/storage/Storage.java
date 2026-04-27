package student_grade_system.storage;

import java.util.List;
/**
 * Generic storage abstraction for saving and retrieving entities.
 *
 * @param <T> type of objects to store
 */
public interface Storage<T> {
    /**
     * Saves the given object if it is not already present.
     *
     * @param t object to save
     */
    void save(T t);
    /**
     * Retrieves a single element from storage.
     * <p>
     * NOTE: Current implementation removes the element from storage.
     *
     * @return retrieved element
     * @throws IndexOutOfBoundsException if storage is empty
     */
    T load();
    /**
     * Returns all stored elements as an unmodifiable list.
     *
     * @return list of all stored elements
     */
    List<T> loadAll();
}

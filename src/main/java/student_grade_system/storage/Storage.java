package student_grade_system.storage;

import java.util.List;

public interface Storage<T> {
    void save(T t);
    T load();
    List<T> loadAll();
}

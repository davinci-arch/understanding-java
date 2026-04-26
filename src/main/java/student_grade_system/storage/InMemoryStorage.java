package student_grade_system.storage;

import java.util.ArrayList;
import java.util.List;

public class InMemoryStorage<T> implements Storage<T> {

    private List<T> storage = new ArrayList<>();
    private int innerCounter = 0;
    @Override
    public void save(T o) {
        if (!storage.contains(o)) {
            storage.add(o);
            innerCounter++;
        }
    }

    @Override
    public T load() {
        innerCounter--;
        var item = storage.get(storage.size() - 1);
        storage.remove(storage.size() - 1);
        return item;
    }

    @Override
    public List<T> loadAll() {
        return List.copyOf(storage);
    }
}

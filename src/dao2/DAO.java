package dao2;

import java.util.List;

public interface DAO<T> {
    void add(T object);
    T get(int id);
    void delete(T object);
    void update(T object);
    List<T> getAll();
}

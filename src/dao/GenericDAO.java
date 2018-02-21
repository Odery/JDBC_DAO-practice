package dao;

import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<T> {
     T create() throws PersistException;
     void persist(T object) throws PersistException;
     T getByPK(int pk) throws PersistException;
     void update(T object) throws PersistException;
     void delete(T object) throws PersistException;
     List<T> getAll() throws PersistException;
}

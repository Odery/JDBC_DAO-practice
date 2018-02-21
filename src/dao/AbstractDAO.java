package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public abstract class AbstractDAO<T> implements GenericDAO<T>{
    private Connection connection;
    protected abstract String getSelectQuery();
    protected abstract String getUpdateQuery();
    protected abstract String getInsertQuery();
    protected abstract String getDeleteQuery();
    protected abstract void prepareForUpdate(PreparedStatement statement,T object);
    protected abstract void prepareForDelete(PreparedStatement statement,T object);
    protected abstract void prepareForInsert(PreparedStatement statement,T object);

    protected abstract List<T> parseResultSet(ResultSet resultSet);

    @Override
    public T getByPK(int pk) throws PersistException{
        List<T> list;
        try{
            PreparedStatement statement = connection.prepareStatement((getSelectQuery()+" WHERE id = ?"));
            statement.setInt(1,pk);
            list = parseResultSet(statement.getResultSet());
        }catch (SQLException ex){
            throw new PersistException(ex.getMessage());
        }
        if(list.size()>1){
            throw new PersistException("More then one result");
        }
        return (list.size()==0||list == null) ?null :(list.iterator().next());
    }
    @Override
    public List<T> getAll() throws PersistException{
        List<T> list;
        try{
            PreparedStatement statement = connection.prepareStatement(getSelectQuery());
            list = parseResultSet(statement.getResultSet());
        }catch (SQLException ex){
            throw new PersistException(ex.getMessage());
        }
        return list;
    }
    @Override
    public void update(T object)throws PersistException{
        try{
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(getUpdateQuery());
            prepareForUpdate(statement,object);
            int count = statement.executeUpdate();
            if (count>1){
                connection.rollback();
                throw new PersistException("More then one records updating!");
            }
            connection.commit();
        }catch (SQLException ex){
            throw new PersistException(ex.getMessage());
        }

    }
    @Override
    public void delete(T object)throws PersistException{
        try{
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(getDeleteQuery());
            prepareForDelete(statement,object);
            int count = statement.executeUpdate();
            if (count>1){
                connection.rollback();
                throw new PersistException("More then one records deleting!");
            }
            connection.commit();
        }catch (SQLException ex){
            throw new PersistException(ex.getMessage());
        }
    }
    @Override
    public void persist (T object) throws PersistException{
        if(object == null){
            throw new PersistException("Object = null");
        }
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(getInsertQuery());
            prepareForInsert(statement,object);
            int count = statement.executeUpdate();
            if(count>1){
                connection.rollback();
                throw new PersistException("Updatibg more then one record");
            }
            connection.commit();
        }catch (SQLException ex){
            throw new PersistException("");
        }
    }
}

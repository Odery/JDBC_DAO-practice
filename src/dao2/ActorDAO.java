package dao2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActorDAO implements DAO<Actor> {
    private Connection connection;

    public ActorDAO (Connection connection){
        this.connection = connection;
    }
    @Override
    public void add(Actor actor){
        if(actor == null)
            throw new NullPointerException("actor = null");
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement("INSERT INTO actor (first_name,last_name) VALUES (?,?)");
            statement.setString(1,actor.getFirst_name());
            statement.setString(2,actor.getLast_name());
            statement.execute();
            ResultSet rs = statement.executeQuery("SELECT * FROM actor WHERE actor_id =  LAST_INSERT_ID()");
            rs.next();
            actor.setActor_id(rs.getInt("actor_id"));
            actor.setLast_update(rs.getDate("last_update"));
            connection.commit();
            statement.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public Actor get(int id){
        Actor actor = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM actor WHERE actor_id = ?");
            statement.setInt(1,id);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            rs.next();
            actor = new Actor(rs.getInt("actor_id"),rs.getString("first_name"),rs.getString("last_name"),rs.getDate("last_update"));
            statement.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return actor;
    }

    @Override
    public void delete(Actor actor){
        if(actor == null)
            throw new NullPointerException("Actor = null");
        else if (actor.getActor_id() == 0)
            throw new NullPointerException("Temp Actor can't be deleted (first add it)");
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement("DELETE FROM actor WHERE actor_id = ?");
            statement.setInt(1,actor.getActor_id());
            statement.execute();
            actor = null;
            connection.commit();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Actor actor){
        if(actor == null)
            throw new NullPointerException("Actor = null");
        else if (actor.getActor_id() == 0)
            throw new NullPointerException("Temp Actor can't be updated (first add it)");
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement("UPDATE  actor SET first_name = ?,last_name = ? WHERE actor_id = ?");
            statement.setString(1,actor.getFirst_name());
            statement.setString(2,actor.getLast_name());
            statement.setInt(3,actor.getActor_id());
            statement.execute();
            statement = connection.prepareStatement("SELECT * FROM actor WHERE id = ?");
            statement.setInt(1,actor.getActor_id());
            statement.execute();
            ResultSet rs = statement.getResultSet();
            rs.next();
            actor.setLast_update(rs.getDate("last_update"));
            connection.commit();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public List<Actor> getAll(){
        List<Actor> list =new ArrayList<Actor>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM actor");
            statement.execute();
            ResultSet rs = statement.getResultSet();
            while (rs.next())
                list.add(new Actor(rs.getInt("actor_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getDate("last_update")));
            statement.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return list;
    }
}

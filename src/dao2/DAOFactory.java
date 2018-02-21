package dao2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOFactory {
    private static Connection connection;
    private static final String url = "jdbc:mysql://localhost:3306/sakila";
    private static final String user = "root";
    private static final String pass = "root";

    public static ActorDAO getActorDAO() throws SQLException{
        connection = DriverManager.getConnection(url,user,pass);
        return new ActorDAO(connection);
    }
}

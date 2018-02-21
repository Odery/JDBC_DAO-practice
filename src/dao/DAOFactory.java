package dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface DAOFactory {
    public Connection getConnection() throws SQLException;

    public AbstractDAO<Group> getGroupDao(Connection connection);
    public AbstractDAO<Student> getStudentDao(Connection connection);
}

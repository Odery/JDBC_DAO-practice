import java.sql.*;

public class Main {
    private static final String url = "jdbc:mysql://localhost/health";
    private static final String user = "root";
    private static final String pass = "root";
    private static Connection con;
    private static Statement  stm;
    private static void getConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url,user,pass);
            stm = con.createStatement();
        }catch(ClassNotFoundException | SQLException ex){
            ex.printStackTrace();
        }
    }
    public static void main (String[] args){
        getConnection();
        try {
            DatabaseMetaData dmd = con.getMetaData();
            ResultSet rs = dmd.getColumns(null,null,"langua",null);
            while (rs.next()){
                System.out.println(rs.getString("column_name"));
            }
            con.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}

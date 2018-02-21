package dao2;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ActorDAO dao = null;
        try {
           dao = DAOFactory.getActorDAO();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        dao.get(202).show();
    }
}

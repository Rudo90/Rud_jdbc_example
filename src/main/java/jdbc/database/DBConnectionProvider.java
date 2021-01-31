package jdbc.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionProvider {

    private static volatile DBConnectionProvider instance;

    public static DBConnectionProvider getProvider() {

        if (instance == null) {
            synchronized (DBConnectionProvider.class) {
                if (instance == null) {
                    instance = new DBConnectionProvider();
                }
            }
        }
        return instance;
    }

    public Connection getConnection(){

        try {
          return   DriverManager.getConnection("jdbc:mysql://localhost:3306/my_database", "root", "root");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }



}

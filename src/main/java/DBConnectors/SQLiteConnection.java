package DBConnectors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteConnection implements DBConnection {

    private Connection con;

    @Override
    public void connect(String path, String password)
    {
        if(con!=null) {
            System.out.println("Already connected");
            return;
        }

        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:"+path);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closeConnection()
    {
        if(con!=null) {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public Connection getConnection() {
        return con;
    }
}

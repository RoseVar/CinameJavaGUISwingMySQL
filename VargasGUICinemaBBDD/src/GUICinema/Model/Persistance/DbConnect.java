
package GUICinema.Model.Persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Roser
 */
public class DbConnect {
    static final String DRIVER = "com.mysql.jdbc.Driver";
    //static final String DRIVER = "com.mysql.cj.jdbc.Driver";      
    static final String PROTOCOL = "jdbc:mysql:";
    static final String HOST = "127.0.0.1";
    static final String BD_NAME = "cinemagui";
    static final String USER = "cinemausr";
    static final String PASSWORD = "cinemapwd";

    public static void loadDriver() throws ClassNotFoundException {
        Class.forName(DRIVER);
    }
    
    /**
     * Methof for getting and returning a connection to database
     * @return connection created with details of the class
     * @throws java.sql.SQLException
     */
    public static Connection getConnection() throws SQLException {
        final String BD_URL = String.format("%s//%s/%s", PROTOCOL, HOST, BD_NAME);
        Connection conn = null;
        conn = DriverManager.getConnection(BD_URL, USER, PASSWORD);
        return conn;
    }
}


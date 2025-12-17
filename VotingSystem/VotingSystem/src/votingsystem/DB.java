package votingsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {

    // This creates a file named voting.db in your project folder
    private static final String URL = "jdbc:sqlite:voting.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}

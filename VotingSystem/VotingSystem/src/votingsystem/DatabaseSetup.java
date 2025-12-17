package votingsystem;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseSetup {

    public static void init() {
        try (Connection conn = DB.connect();
             Statement stmt = conn.createStatement()) {

            // USERS TABLE
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS users (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    username TEXT UNIQUE,
                    password TEXT,
                    role TEXT
                )
            """);

            // CANDIDATES TABLE
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS candidates (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT
                )
            """);

            // VOTES TABLE (one vote per user)
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS votes (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    user_id INTEGER UNIQUE,
                    candidate_id INTEGER
                )
            """);

            // Insert default users (only if empty)
            stmt.execute("""
                INSERT OR IGNORE INTO users (id, user_id, password, role)
                VALUES
                (1, 'admin', 'admin123', 'admin'),
                (2, 'voter', '1234', 'voter')
            """);

            // Insert default candidates
            stmt.execute("""
                INSERT OR IGNORE INTO candidates (id, name)
                VALUES
                (1, 'Alice'),
                (2, 'Bob'),
                (3, 'Charlie')
            """);

            System.out.println("Database setup complete.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package task3_19;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Batching {
    public static void main(String[] args) throws SQLException {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/shop",
                "postgres",
                "postgresql")) {
            DBUtil.renewDatabase(connection);
            try (PreparedStatement stmt = connection.prepareStatement(
                    "UPDATE managers SET personal_id=?;")) {
                    stmt.setString(1, "000123");
                    stmt.addBatch();

                stmt.execute();

            }
        }
    }
}

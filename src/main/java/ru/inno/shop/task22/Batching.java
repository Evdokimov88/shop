package ru.inno.shop.task22;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Batching {
   private static Logger logger = LoggerFactory.getLogger(Batching.class);
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
                logger.info("Меняем ID...");
                stmt.execute();

            }
        }
        catch (SQLException e){
            logger.error("Что-то пошло не так... "+e.getMessage());
        }


    }
}

package ru.inno.shop.task3_19;

import java.sql.*;

public class SavePoint {
        public static void main(String[] args) throws SQLException {
            try (Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/shop",
                    "postgres",
                    "postgresql")) {
                DBUtil.renewDatabase(connection);
                try (Statement statement = connection.createStatement()) {
                    connection.setAutoCommit(false);
                    for (int i=0; i<5; i++) {
                        statement.executeUpdate(
                                "INSERT INTO clients (first_name, last_name, phone, email)\n"
                                        + "VALUES\n"
                                        + "('Клиент" + i + "', "+ "'Партнеров"+i+"', '8888888"+i+"', 'vip"+i+"@dot.com');"

                        );
                    }
                    Savepoint savepoint = connection.setSavepoint();
                    for (int i=5; i<10; i++) {
                        statement.executeUpdate(
                                "INSERT INTO clients (first_name, last_name, phone, email)\n"
                                        + "VALUES\n"
                                        + "('Клиент" + i + "', "+ "'Партнеров"+i+"', '8888888"+i+"', 'vip"+i+"@dot.com');"

                        );
                    }

                    connection.rollback(savepoint);
                    connection.commit();

                } catch (SQLException e) {
                    connection.rollback();
                    e.printStackTrace();
                }
            }
        }
    }

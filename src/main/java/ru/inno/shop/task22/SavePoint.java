package ru.inno.shop.task22;


import java.sql.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SavePoint {
    private static Logger logger = LoggerFactory.getLogger(SavePoint.class);
        public static void main(String[] args) throws SQLException {
            try (Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/shop",
                    "postgres",
                    "postgre")) {
                DBUtil.renewDatabase(connection);
                try (Statement statement = connection.createStatement()) {
                    connection.setAutoCommit(false);
                    for (int i=0; i<5; i++) {

                        logger.info("Добавляем клиента...");
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
                    logger.info("Точка сохранения...");
                    connection.rollback(savepoint);
                    connection.commit();

                } catch (SQLException e) {
                    connection.rollback();
                    logger.error("Что-то пошло не так... "+e.getMessage());
                }
            }
            catch (SQLException e){
                logger.error("Что-то пошло не так... "+e.getMessage());
            }
        }
    }

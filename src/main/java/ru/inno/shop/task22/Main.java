package ru.inno.shop.task22;

import java.sql.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class Main {
    /**
     * Задание 1. Взял за основу ДЗ 19:
     *
     *      Заменить весь вывод System.out.println на логгирование любым выбранным фреймворком.
     *      Продумать схему использования уровней логгирования (Напр. уровень DEBUG для событий используемых в отладке приложения и не отображаемых при запуске приложения в штатном режиме).
     *
     * Задание 2. Настроить логгирование:
     *
     *     Разделить условно логи на системные, логи безопасности, логи бизнес событий.
     *     Настроить формат для логов каждого из видов.
     *     Продумать удобный вид хранения для логов каждого из видов.
     *     Использовать запись в заранее заготовленную таблицу в базе данных.
     *     Использовать запись в файл с ограничением по размеру файла, сроку хранения файла и ротацией файлов.
     */
    private static Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/shop",
                "postgres",
                "postgre")) {
            DBUtil.renewDatabase(connection);
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM goods WHERE price < ? and quantity < ?")) {
                preparedStatement.setFloat( 1, 100);
                preparedStatement.setInt( 2, 200);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    int count=0;
                    while (resultSet.next()) {
                        String data = "{id = " + resultSet.getInt("id") + "\t"
                                + "name = " + resultSet.getString("name") + "\t"
                                + "price = " + resultSet.getInt("price") + "\t"
                                + "quantity = " + resultSet.getString("quantity") + "}";


                        logger.info("row = " + data);
                        count++;
                    }
                    logger.debug("получено " + count + " записей");
                }
            }
        } catch (SQLException exception) {
            logger.error("Что-то пошло не так... "+exception.getMessage());
        }
    }
}

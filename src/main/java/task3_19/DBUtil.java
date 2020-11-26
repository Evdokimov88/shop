package task3_19;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

    private DBUtil() {
    }

    public static void renewDatabase(Connection connection) throws SQLException {
        /*
           Ранее перед созданием подключения к базе (connection) требовалось вручную загружать драйвер,
           Class.forName("org.postgresql.Driver")
           но с приходом JBDC 4.0 драйверов это более не требуется, так как драйвер загружается автоматически.
        */
        int id = (int) (10*Math.random());
        try (Statement statement = connection.createStatement();
        ) {
            statement.execute("-- Database: jdbcDB\n"
                    + "DROP TABLE IF EXISTS goods;"
                    + "\n"
                    + "CREATE TABLE goods (\n"
                    + "    id INT primary key,\n"
                    + "    name varchar(100) NOT NULL,\n"
                    + "    quantity integer NOT NULL,\n"
                    + "    price INT NOT NULL);"
                    + "\n"
                    + "INSERT INTO goods (id, name, quantity, price)\n"
                    + "VALUES\n"
                    + "   ((SELECT random()*(25000-1)+1),'Шариковая ручка', 50000, 12),\n"
                    + "   ((SELECT random()*(25000-1)+1),'Кофрик для мыши', 140, 70),\n"
                    + "   ((SELECT random()*(25000-1)+1),'Смартфон Apple Iphone 12 Pro', 45, 96900),\n"
                    + "   ((SELECT random()*(25000-1)+1),'Чай Принцесса Нури в пакетиках', 300, 76),\n"
                    + "   ((SELECT random()*(25000-1)+1),'Кресло офисное', 12, 3499);"
                    + "\n"
                    + "DROP FUNCTION IF EXISTS multiply(integer);"
                    + "\n"
                    + "CREATE OR REPLACE FUNCTION multiply(a INT, b INT) RETURNS INT AS $$\n"
                    + "DECLARE\n"
                    + "    price int;\n"
                    + "BEGIN\n"
                    + "    SELECT mobile.price INTO price FROM mobile WHERE id = b;\n"
                    + "    return price * a;\n"
                    + "END\n"
                    + "$$ LANGUAGE 'plpgsql';");
        }
    }
}
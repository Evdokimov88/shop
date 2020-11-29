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
                    + "DROP TABLE IF EXISTS managers;"
                    + "DROP TABLE IF EXISTS clients;"
                    + "\n"
                    + "CREATE TABLE goods (\n"
                    + "    id bigserial primary key,\n"
                    + "    name varchar(100) NOT NULL,\n"
                    + "    quantity INT NOT NULL,\n"
                    + "    price FLOAT NOT NULL);"
                    + "\n"
                    + "INSERT INTO goods (name, quantity, price)\n"
                    + "VALUES\n"
                    + "   ('Шариковая ручка', 50000, 12),\n"
                    + "   ('Кофрик для мыши', 140, 70),\n"
                    + "   ('Смартфон Apple Iphone 12 Pro', 45, 96900),\n"
                    + "   ('Чай Принцесса Нури в пакетиках', 300, 76),\n"
                    + "   ('Кресло офисное', 12, 3499);"
                    + "\n"

                    + "\n"
                    + "CREATE TABLE managers (\n"
                    + "    id bigserial primary key,\n"
                    + "    name_manager varchar(100) NOT NULL,\n"
                    + "    personal_id varchar NOT NULL);"
                    + "\n"
                    + "INSERT INTO managers (name_manager, personal_id)\n"
                    + "VALUES\n"
                    + "   ('Ольга', (SELECT random()*(25000-1)+1)),\n"
                    + "   ('Павел', (SELECT random()*(25000-1)+1)),\n"
                    + "   ('Людмила',(SELECT random()*(25000-1)+1)),\n"
                    + "   ('Ярослав',(SELECT random()*(25000-1)+1)),\n"
                    + "   ('Владилен', (SELECT random()*(25000-1)+1));"
                    + "\n"
                    + "CREATE TABLE clients (\n"
                    + "    id bigserial primary key,\n"
                    + "    first_name varchar(100) NOT NULL,\n"
                    + "    last_name varchar(100),\n"
                    + "    phone bigint,\n"
                    + "    email varchar (30) NOT NULL);"
                    + "\n"
                    + "INSERT INTO clients (first_name, last_name, phone, email)\n"
                    + "VALUES\n"
                    + "   ('Александр', 'Пушкин' , 2222222, 'pushkin@gold.com'),\n"
                    + "   ('Сергей', 'Есенин' , 56249233, 'esenin@silver.com'),\n"
                    + "   ('Федор', 'Достоевский' , 9999, 'pushkin@gold.com'),\n"
                    + "   ('Николай', 'Гоголь' , 969696969, 'pushkin@silver.com'),\n"
                    + "   ('Шарлиз', 'Терон' , 896236214578, 'sharliz@teron.com');\n"
                    + "\n");


        }
    }
}
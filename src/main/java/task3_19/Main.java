package task3_19;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/shop",
                "postgres",
                "postgresql")) {
            DBUtil.renewDatabase(connection);
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM goods WHERE price < ? and quantity < ?")) {
                preparedStatement.setFloat( 1, 100);
                preparedStatement.setInt( 2, 200);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        System.out.print("id=" + resultSet.getInt("id"));
                        System.out.print("; name=" + resultSet.getString("name"));
                        System.out.print("; price=" + resultSet.getInt("price"));
                        System.out.println("; quantity=" + resultSet.getString(
                                "quantity"));
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

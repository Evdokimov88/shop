package task3_19;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/shop",
                "postgres",
                "postgre")) {
            DBUtil.renewDatabase(connection);
//            try (PreparedStatement preparedStatement = connection.prepareStatement(
//                    "SELECT * FROM mobile WHERE model = ? and price < ?")) {
//                preparedStatement.setString(1, "FRY1");
//                preparedStatement.setFloat(2, 2000);
//
//                try (ResultSet resultSet = ((PreparedStatement) preparedStatement).executeQuery()) {
//                    while (resultSet.next()) {
//                        System.out.print("id=" + resultSet.getInt("id"));
//                        System.out.print("; model=" + resultSet.getString("model"));
//                        System.out.print("; price=" + resultSet.getInt("price"));
//                        System.out.println("; manufacturer=" + resultSet.getString(
//                                "manufacturer"));
//                    }
//                }
//            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

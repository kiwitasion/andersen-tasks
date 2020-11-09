package managers;

import java.sql.*;

public class UserManager {


    public static void view(Connection conn) throws SQLException {
        String sql = "SELECT * FROM Users";

        Statement statement = conn.createStatement();

        ResultSet result = statement.executeQuery(sql);

        int count = 0;

        while (result.next()) {
            String first_name = result.getString(2);
            String last_name = result.getString(3);
            String login = result.getString(4);

            String output = "User #%d: %s - %s - %s";
            System.out.println(String.format(output, ++count, first_name, last_name, login));
        }
    }


    public static void insert(Connection conn, String first_name, String last_name, String login,
                           String password) throws SQLException {
        String sql = "INSERT INTO Users (first_name, last_name, login, password)" +
                " VALUES (?, ?, ?, ?)";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, first_name);
        statement.setString(2, last_name);
        statement.setString(3, login);
        statement.setString(4, password);


        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new user was inserted successfully!");
        }
    }

    public static void update(Connection conn, String newLogin, String newPassword,
                                  Long user_id) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("UPDATE Users SET login = ?," +
                "password = ? WHERE user_id = ?");

        statement.setString(1, newLogin);
        statement.setString(2, newPassword);
        statement.setLong(3, user_id);
        statement.executeUpdate();
    }

    public static void delete(Connection conn, Long user_id) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("DELETE FROM Users WHERE user_id = ?");

        statement.setLong(1, user_id);
        statement.executeUpdate();
    }


}

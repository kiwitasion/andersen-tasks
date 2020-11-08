package managers;

import java.sql.*;

public class CountryManager {

    public static void view(Connection conn) throws SQLException {
        String sql = "SELECT * FROM Countries";

        Statement statement = conn.createStatement();

        ResultSet result = statement.executeQuery(sql);

        int count = 0;

        while (result.next()) {
            String name = result.getString(2);

            String output = "Order #%d: %s - %s ";
            System.out.println(String.format(output, ++count, name));
        }
    }

    public static void insert(Connection conn, String name) throws SQLException {
        String sql = "INSERT INTO Countries (name) VALUES (?)";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, name);


        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new country was inserted successfully!");
        }
    }

    public static void update(Connection connection, String name, Long country_id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE Countries SET name = ? " +
                "WHERE country_id = ?");

        statement.setString(1, name);
        statement.setLong(2, country_id);
        statement.executeUpdate();
    }

    public static void delete(Connection connection, Long country_id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM Countries WHERE country_id = ?");

        statement.setLong(1, country_id);
        statement.executeUpdate();
    }
}

package managers;

import java.sql.*;

public class TourManager {

    public static void view(Connection conn) throws SQLException {
        String sql = "SELECT * FROM Tours";

        Statement statement = conn.createStatement();

        ResultSet result = statement.executeQuery(sql);

        int count = 0;

        while (result.next()) {
            String name = result.getString(2);
            boolean transfer_included = result.getBoolean(3);
            Long country_id = result.getLong(4);
            Long hotel_id = result.getLong(5);

            String output = "User #%d: %s - %s - %s - %s - %s";
            System.out.println(String.format(output, ++count, name, transfer_included, country_id, hotel_id));
        }
    }

    public static void insert(Connection conn, String name, boolean transfer_included,
                                  Long country_id, Long hotel_id) throws SQLException {
        String sql = "INSERT INTO Tours (name, transfer_included, country_id, hotel_id)" +
                "VALUES (?, ?, ?, ?)";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, name);
        statement.setBoolean(2, transfer_included);
        statement.setLong(3, country_id);
        statement.setLong(4, hotel_id);

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new tour was inserted successfully!");
        }
    }

    public static void update(Connection connection, String name, boolean transfer_included,
                                  Long country_id, Long hotel_id, Long tour_id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE Tours SET name = ?," +
                "transfer_included = ?, country_id = ?, hotel_id = ? WHERE tour_id = ?");

        statement.setString(1, name);
        statement.setBoolean(2, transfer_included);
        statement.setLong(3, country_id);
        statement.setLong(4, hotel_id);
        statement.setLong(5, tour_id);
        statement.executeUpdate();
    }

    public static void delete(Connection connection, Long tour_id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM Tours WHERE tour_id = ?");

        statement.setLong(1, tour_id);
        statement.executeUpdate();
    }
}

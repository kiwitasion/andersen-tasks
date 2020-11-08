package managers;

import java.sql.*;

public class HotelManager {

    public enum ServiceLevel {
        Low, Medium, High, Luxury;
    }

    public static void view(Connection conn) throws SQLException {
        String sql = "SELECT * FROM Hotels";

        Statement statement = conn.createStatement();

        ResultSet result = statement.executeQuery(sql);

        int count = 0;

        while (result.next()) {
            String name = result.getString(2);
            String service_level = result.getString(3);

            String output = "Order #%d: %s - %s - %s";
            System.out.println(String.format(output, ++count, name, service_level));
        }
    }

    public static void insert(Connection conn, String name, ServiceLevel service_level) throws SQLException {
        String sql = "INSERT INTO Hotels (name, service_level) VALUES (?, ?)";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, name);
        statement.setString(2, service_level.name());

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new hotel was inserted successfully!");
        }
    }

    public static void update(Connection connection, String name, ServiceLevel service_level,
                                   Long hotel_id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE Hotels SET name = ?, " +
                "service_level = ? WHERE hotel_id = ?");

        statement.setString(1, name);
        statement.setString(2, service_level.name());
        statement.setLong(3, hotel_id);
        statement.executeUpdate();
    }

    public static void delete(Connection connection, Long hotel_id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM Hotels WHERE hotel_id = ?");

        statement.setLong(1, hotel_id);
        statement.executeUpdate();
    }
}

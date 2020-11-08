package managers;

import java.sql.*;

public class OrderManager{

    public static void view(Connection conn) throws SQLException {
        String sql = "SELECT * FROM Orders";

        Statement statement = conn.createStatement();

        ResultSet result = statement.executeQuery(sql);

        int count = 0;

        while (result.next()) {
            String number = result.getString(2);
            int price = result.getInt(3);
            Long tour_id = result.getLong(4);
            Long user_id = result.getLong(5);

            String output = "Order #%d: %s - %s - %s - %s - %s";
            System.out.println(String.format(output, ++count, number, price, tour_id, user_id));
        }
    }

    public static void insert(Connection conn, String number, Integer price, Long tour_id, Long user_id) throws SQLException {
        String sql = "INSERT INTO Orders (number, price, tour_id, user_id) VALUES (?, ?, ?, ?)";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, number);
        statement.setInt(2, price);
        statement.setLong(3, tour_id);
        statement.setLong(4, user_id);

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new order was inserted successfully!");
        }

    }

    public static void update(Connection conn, String newNumber, Long order_id) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("UPDATE Orders SET number = ? " +
                "WHERE order_id = ?");

        statement.setString(1, newNumber);
        statement.setLong(2, order_id);
        statement.executeUpdate();
    }

    public static void delete(Connection connection, Long order_id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM Orders WHERE order_id = ?");

        statement.setLong(1, order_id);
        statement.executeUpdate();
    }
}

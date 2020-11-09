package managers;


import java.sql.*;

public class ReviewManager {

    public static void view(Connection conn) throws SQLException {
        String sql = "SELECT * FROM Reviews";

        Statement statement = conn.createStatement();

        ResultSet result = statement.executeQuery(sql);

        int count = 0;

        while (result.next()) {
            String text = result.getString(2);
            Long user_id = result.getLong(3);
            Long hotel_id = result.getLong(4);

            String output = "Order #%d: %s - %s - %s - %s";
            System.out.println(String.format(output, ++count, text, user_id, hotel_id));
        }
    }

    public static void insert(Connection conn, String text, Long user_id, Long hotel_id) throws SQLException {
        String sql = "INSERT INTO Reviews (text, user_id, hotel_id) VALUES (?, ?, ?)";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, text);
        statement.setLong(2, user_id);
        statement.setLong(3, hotel_id);

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new review was inserted successfully!");
        }
    }

    public static void update(Connection connection, String text, Long user_id,
                                    Long hotel_id, Long review_id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE Reviews SET text = ?, " +
                "user_id = ?, hotel_id WHERE review_id = ?");

        statement.setString(1, text);
        statement.setLong(2, user_id);
        statement.setLong(3, hotel_id);
        statement.setLong(4, review_id);
        statement.executeUpdate();
    }

    public static void delete(Connection connection, Long review_id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM Reviews WHERE review_id = ?");

        statement.setLong(1, review_id);
        statement.executeUpdate();
    }
}

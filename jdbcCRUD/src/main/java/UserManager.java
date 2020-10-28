import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.*;

public class UserManager {

    enum ServiceLevel {
        Low, Medium, High, Luxury;
    }

    public static void viewUsers(Connection conn) throws SQLException {
        String sql = "SELECT * FROM Users";

        Statement statement = conn.createStatement();

        ResultSet result = statement.executeQuery(sql);

        int count = 0;

        while (result.next()) {
            String firstname = result.getString(2);
            String lastName = result.getString(3);
            String login = result.getString(4);
            int order_id = result.getInt(6);
            int review_id = result.getInt(7);

            String output = "User #%d: %s - %s - %s - %s - %s";
            System.out.println(String.format(output, ++count, firstname, lastName, login, order_id, review_id));
        }
    }

    public static void insertCountry(Connection conn, String name) throws SQLException {
        String sql = "INSERT INTO Countries (name) VALUES (?)";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, name);


        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
//            System.out.println("A new country was inserted successfully!");
        }
    }

    public static void insertReview(Connection conn, String text) throws SQLException {
        String sql = "INSERT INTO Reviews (text) VALUES (?)";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, text);


        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
//            System.out.println("A new review was inserted successfully!");
        }
    }

    public static void insertHotel(Connection conn, String name, ServiceLevel s, int review_id) throws SQLException {
        String sql = "INSERT INTO Hotels (name, serviceLevel, review_id) VALUES (?, ?, ?)";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, name);
        statement.setString(2, s.name());
        statement.setInt(3, review_id);


        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
//            System.out.println("A new hotel was inserted successfully!");
        }
    }

    public static void insertTour(Connection conn, String name, int country_id, int hotel_id,
                           boolean transfer) throws SQLException {
        String sql = "INSERT INTO Tours (name, country_id, hotel_id, transfer_included)" +
                "VALUES (?, ?, ?, ?)";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, name);
        statement.setInt(2, country_id);
        statement.setInt(3, hotel_id);
        statement.setBoolean(4, transfer);

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
//            System.out.println("A new tour was inserted successfully!");
        }
    }

    public static void insertOrder(Connection conn, String number, int tour_id, int price) throws SQLException {
        String sql = "INSERT INTO Orders (number, tour_id, price) VALUES (?, ?, ?)";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, number);
        statement.setInt(2, tour_id);
        statement.setInt(3, price);

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
//            System.out.println("A new order was inserted successfully!");
        }
    }


    public static void insertUser(Connection conn, String firstName, String lastName, String login,
                           String password, int order_id, int review_id) throws SQLException {
        String sql = "INSERT INTO Users (firstName, lastName, login, password, order_id, review_id)" +
                " VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, firstName);
        statement.setString(2, lastName);
        statement.setString(3, login);
        statement.setString(4, password);
        statement.setInt(5, order_id);
        statement.setInt(6, review_id);

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
//            System.out.println("A new user was inserted successfully!");
        }
    }

    public static void updateUser(Connection connection, String newLogin, String newPassword,
                                  int user_id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE Users SET login = ?," +
                "password = ? WHERE user_id = ?");

        statement.setString(1, newLogin);
        statement.setString(2, newPassword);
        statement.setInt(3, user_id);
        statement.executeUpdate();
    }

    public static void deleteUser(Connection connection, int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM Users WHERE user_id = ?");

        statement.setInt(1, id);
        statement.executeUpdate();
    }

    private static DataSource getDatasource() {
        HikariConfig config = new HikariConfig();
        config.setUsername("root");
        config.setPassword("Rundmc559");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/travelagency");
        DataSource ds = new HikariDataSource(config);
        return ds;
    }

    public static void main(String[] args) {
        DataSource dataSource = getDatasource();

        try (Connection connection = dataSource.getConnection()){
            connection.setAutoCommit(false);

            System.out.println("Connected");

            viewUsers(connection);
            System.out.println("-----------------");

            insertCountry(connection, "Greece");
            insertReview(connection, "Not bad");
            insertHotel(connection, "Holiday Village", ServiceLevel.Medium, 3);
            insertTour(connection, " Fabulous Crete", 3, 3, true);
            insertOrder(connection, "654", 3, 700);
            insertUser(connection, "Steven", "Miller", "s.miller",
                    "asdff", 3, 3);
            viewUsers(connection);
            System.out.println("-----------------");

            updateUser(connection, "sm.alex", "qwerty1234ytrewq", 1);
            viewUsers(connection);
            System.out.println("-----------------");

            deleteUser(connection, 2);
            viewUsers(connection);
            System.out.println("-----------------");

            connection.rollback();//Make rollback for simple test
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}

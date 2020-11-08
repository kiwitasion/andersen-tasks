package main;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import managers.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    private static DataSource getDatasource() {
        HikariConfig config = new HikariConfig();
        config.setUsername("root");
        config.setPassword("Rundmc559");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/travelagency");
        return new HikariDataSource(config);
    }

    public static void main(String[] args) {
        DataSource dataSource = getDatasource();

        try (Connection connection = dataSource.getConnection()){
            connection.setAutoCommit(false);

            System.out.println("Connected");

//            UserManager.delete(connection, 1);
//            UserManager.delete(connection, 2);
//            OrderManager.delete(connection, 1);
//            OrderManager.delete(connection, 2);
//            TourManager.delete(connection, 1);
//            TourManager.delete(connection, 2);
//            CountryManager.delete(connection, 1);
//            CountryManager.delete(connection, 2);
//            HotelManager.delete(connection, 1);
//            HotelManager.delete(connection, 2);
//            ReviewManager.delete(connection,1);
//            ReviewManager.delete(connection,2);
//
            CountryManager.insert(connection, "Greece");
            CountryManager.insert(connection, "Turkey");
            HotelManager.insert(connection, "Holiday Village", HotelManager.ServiceLevel.Medium);
            HotelManager.insert(connection, "Resort", HotelManager.ServiceLevel.High);
            TourManager.insert(connection, "Fabulous Crete", true, 1L, 1L);
            TourManager.insert(connection, "Istanbul tour", true, 2L, 2L);
            UserManager.insert(connection, "Steven", "Miller", "s.miller", "asdff");
            UserManager.insert(connection, "John", "Smith", "j.smith", "qwerty");
            ReviewManager.insert(connection,"Not bad", 1L, 1L);
            ReviewManager.insert(connection,"Very nice", 2L, 2L);
            OrderManager.insert(connection, "654", 700, 1L, 1L);
            OrderManager.insert(connection, "854", 1000, 2L, 2L);

            UserManager.view(connection);
            System.out.println("-----------------");

            UserManager.update(connection, "m.steven", "qwerty1234ytrewq", 1L);
            UserManager.view(connection);
            System.out.println("-----------------");

            UserManager.delete(connection, 2L);
            UserManager.view(connection);
            System.out.println("-----------------");

            connection.rollback();//Make rollback for simple test
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}

package hibernate;

import hibernate.actions.*;
import hibernate.models.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {

        SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

        OperationCountry operationCountry = new OperationCountry(sf);
        OperationReview operationReview = new OperationReview(sf);
        OperationHotel operationHotel = new OperationHotel(sf);
        OperationUser operationUser = new OperationUser(sf);
        OperationTour operationTour = new OperationTour(sf);
        OperationOrder operationOrder = new OperationOrder(sf);

        Country country1 = new Country("Turkey");
        Hotel hotel1 = new Hotel("Resort-Kemer", Hotel.ServiceLevel.High);
        Tour tour1 = new Tour("Fabulous turkey", true);
        tour1.setCountry(country1);
        tour1.setHotel(hotel1);
        country1.setTour(tour1);

        operationCountry.createCountry(country1);
        operationHotel.createHotel(hotel1);
        operationTour.createTour(tour1);

        User user1 = new User("Alex", "Smith", "al.smith", "12345");
        User user2 = new User("John", "Williams", "j.williams", "54321");

        Review review1 = new Review("Nice hotel");
        Review review2 = new Review("Gooood!");

        hotel1.setReviews(review1);
        hotel1.setReviews(review2);//adding reviews to hotel

        review1.setHotel(hotel1);
        review2.setHotel(hotel1);
        review1.setUser(user1);
        review2.setUser(user2);

        user1.setReview(review1);
        user2.setReview(review2);//adding reviews to user


        operationUser.createUser(user1);
        operationUser.createUser(user2);

        operationHotel.updateHotel(hotel1);


        Order order1 = new Order("12qw2", 500);
        Order order2 = new Order("113we", 500);

        tour1.setOrder(order1);
        tour1.setOrder(order2);
        order1.setTour(tour1);
        order2.setTour(tour1);
        order1.setUser(user1);
        order2.setUser(user2);

        user1.setOrder(order1);
        user2.setOrder(order2);

        operationUser.updateUser(user1);
        operationUser.updateUser(user2);
        operationTour.updateTour(tour1);


    }
}

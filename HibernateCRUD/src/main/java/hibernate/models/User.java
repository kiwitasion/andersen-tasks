package hibernate.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long user_id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Order> orders = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Review> reviews = new HashSet<>();

    private String first_name;
    private String last_name;
    private String login;
    private String password;

    public User() {
    }

    public User(String first_name, String last_name, String login, String password) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.login = login;
        this.password = password;
    }

    public Long getUser_id() {
        return user_id;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrder(Order order) {
        orders.add(order);
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReview(Review review) {
        reviews.add(review);
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

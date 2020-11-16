package ru.kivit.models;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
@ToString
public class User {

    @Id
    @GeneratedValue
    private Long user_id;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Order> orders = new HashSet<>();

    @ToString.Exclude
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

    public void setOrder(Order order) {
        orders.add(order);
    }

    public void setReview(Review review) {
        reviews.add(review);
    }


}

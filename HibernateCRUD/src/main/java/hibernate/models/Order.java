package hibernate.models;

import javax.persistence.*;

@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue
    private Long order_id;

    @ManyToOne
    @JoinColumn(name = "tour_id")
    private Tour tour;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String number;
    private Integer price;

    public Order() {
    }

    public Order(String number, Integer price) {
        this.number = number;
        this.price = price;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}

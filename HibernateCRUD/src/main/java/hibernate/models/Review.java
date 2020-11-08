package hibernate.models;

import javax.persistence.*;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue
    private Long review_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    private String text;

    public Review() {
    }

    public Review(String text) {
        this.text = text;
    }

    public Long getReview_id() {
        return review_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}



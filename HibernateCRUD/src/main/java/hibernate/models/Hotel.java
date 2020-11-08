package hibernate.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "hotels")
public class Hotel {

    public enum ServiceLevel{
        Low, Medium, High, Lux;
    }

    @Id
    @GeneratedValue
    private Long hotel_id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hotel", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Review> reviews = new HashSet<>();

    private String name;
    private ServiceLevel service_level;

    public Hotel() {
    }

    public Hotel(String name, ServiceLevel serviceLevel) {
        this.name = name;
        this.service_level = serviceLevel;
    }

    public Long getHotel_id() {
        return hotel_id;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ServiceLevel getServiceLevel() {
        return service_level;
    }

    public void setServiceLevel(ServiceLevel service_level) {
        this.service_level = service_level;
    }

    public void setReviews(Review review) {
        reviews.add(review);
    }
}

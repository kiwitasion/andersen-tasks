package hibernate.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tours")
public class Tour {

    @Id
    @GeneratedValue
    private Long tour_id;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @OneToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tour", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private final Set<Order> orders = new HashSet<>();

    private String name;
    private Boolean transfer_included;

    public Tour() {
    }

    public Tour(String name, Boolean transfer_included) {
        this.name = name;
        this.transfer_included = transfer_included;
    }

    public Long getTour_id() {
        return tour_id;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrder(Order order) {
        orders.add(order);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getTransfer_included() {
        return transfer_included;
    }

    public void setTransfer_included(Boolean transfer_included) {
        this.transfer_included = transfer_included;
    }
}

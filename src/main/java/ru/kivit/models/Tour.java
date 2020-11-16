package ru.kivit.models;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "tours")
@ToString
public class Tour {

    @Id
    @GeneratedValue
    private Long tour_id;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @ToString.Exclude
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

    public void setOrder(Order order) {
        orders.add(order);
    }

}

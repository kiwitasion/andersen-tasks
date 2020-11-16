package ru.kivit.models;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@Table(name = "hotels")

public class Hotel {

    public enum ServiceLevel {
        Low, Medium, High, Lux
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

//    public ServiceLevel getService_level() {
//        return service_level;
//    }

    public void setReviews(Review review) {
        reviews.add(review);
    }

}

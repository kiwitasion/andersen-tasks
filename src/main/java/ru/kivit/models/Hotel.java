package ru.kivit.models;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Entity
@Data
@Table(name = "hotels")
@ToString
public class Hotel {

    public enum ServiceLevel {
        Low, Medium, High, Lux;
    }

    @Id
    @GeneratedValue
    private Long hotel_id;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hotel", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Review> reviews = new HashSet<>();
    private String name;

    @Enumerated(EnumType.STRING)
    private ServiceLevel service_level;

    public void addReview(Review review) {
        reviews.add(review);
    }

}

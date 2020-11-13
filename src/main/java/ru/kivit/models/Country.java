package ru.kivit.models;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Data
@Table(name = "countries")
@ToString
public class Country {

    @Id
    @GeneratedValue
    private Long country_id;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "country", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private final Set<Tour> tours = new HashSet<>();

    private String name;

    public Country() {
    }

    public Country(String name) {
        this.name = name;
    }


    public void setTour(Tour tour) {
        tours.add(tour);
    }


}

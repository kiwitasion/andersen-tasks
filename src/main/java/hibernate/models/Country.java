package hibernate.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "countries")
public class Country {

    @Id
    @GeneratedValue
    private Long country_id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "country", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private final Set<Tour> tours = new HashSet<>();

    private String name;

    public Country() {
    }

    public Country(String name) {
        this.name = name;
    }

    public Long getCountry_id() {
        return country_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Tour> getTours() {
        return tours;
    }

    public void setTour(Tour tour) {
        tours.add(tour);
    }

}

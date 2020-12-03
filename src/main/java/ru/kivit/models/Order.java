package ru.kivit.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
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

    private Long number;
    private Integer price;

}

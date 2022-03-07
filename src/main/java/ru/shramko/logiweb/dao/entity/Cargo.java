package ru.shramko.logiweb.dao.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="cargos")
@Data
public class Cargo extends AbstractEntity {

    @Column(name = "number")
    private String number;

    @Column(name = "name")
    private String name;

    @Column(name = "weight")
    private Double weight; //

    @Column(name = "status")
    private String status;

    @OneToOne
    @JoinColumn(name="city_id")
    private City startCity;

    @OneToOne
    @JoinColumn(name="end_city_id")
    private City endCity;

    @ManyToOne
    @JoinColumn(name="order_id")
    private Order order;
}


package ru.shramko.logiweb.dao.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="points")
@Data @NoArgsConstructor
public class Point extends AbstractEntity {

    @OneToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private City city;

    @OneToOne
    @JoinColumn(name = "cargo_id", referencedColumnName = "id")
    private Cargo cargo;

    @ManyToOne
    @JoinColumn(name="order_id")
    private Order order;

    @Column(name = "type")
    private String type;

    public Point(Cargo cargo, String type, Order order, City city) {
        super();
        this.cargo = cargo;
        this.type = type;
        this.order = order;
        this.city = city;
    }
}

package ru.shramko.logiweb.dao.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "trucks")
@Data
public class Truck extends AbstractEntity {

    @Column(name = "reg")
    private String reg;

    @Column(name = "shift")
    private Integer shift;

    @Column(name = "capacity")
    private Double capacity;

    @Column(name = "state")
    private String state;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @OneToOne
    @JoinColumn(name = "city_id")
    private City city;

    @Override
    public String toString() {
        String orderNum;

        if (order == null) {
            orderNum = " ";
        } else {
            orderNum = " order=" + order.getNumber();
        }
        return "Truck{" +
                "reg='" + reg + '\'' +
                ", shift=" + shift +
                ", capacity=" + capacity +
                ", state='" + state + '\'' +
                ", city=" + city +
                orderNum +'}';
    }
}

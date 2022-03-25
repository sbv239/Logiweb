package ru.shramko.logiweb.dao.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name ="drivers")
@Data
public class Driver extends AbstractEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "number")
    private String number;

    @Column(name = "hours")
    private Integer hours;

    @Column(name = "status")
    private String status;

    @OneToOne
    @JoinColumn(name = "city_id")
    private City city;

    @OneToOne
    @JoinColumn(name = "truck_id")
    private Truck truck;

    @ManyToOne
    @JoinColumn(name="order_id")
    private Order order;

    @Override
    public String toString() {

        String orderNum;

        if (order == null) {
            orderNum = " ";
        } else {
            orderNum = " order=" + order.getNumber();
        }

        return "Driver{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", number='" + number + '\'' +
                ", hours=" + hours +
                ", status='" + status + '\'' +
                ", city=" + city +
                orderNum + '}';
    }
}

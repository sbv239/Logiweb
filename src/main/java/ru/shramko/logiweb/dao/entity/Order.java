package ru.shramko.logiweb.dao.entity;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="orders")
@Data @NoArgsConstructor
public class Order extends AbstractEntity {

    @Column(name = "number")
    private String number;

    @Column(name = "status")
    private String status;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "truck_id")
    private Truck truck;

    @OneToMany(fetch = FetchType.EAGER, mappedBy="order")
    private List<Point> pointList;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "start_city_id")
    private City startCity;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "end_city_id")
    private City endCity;

    @OneToMany(mappedBy="order")
    private List<Driver> driverList;

    @Override
    public String toString() {
        String truckReg;
        if (truck == null) {
            truckReg = "-";
        } else {
            truckReg = truck.getReg();
        }
        return "Order{" +
                "number='" + number + '\'' +
                ", status=" + status +
                ", truck=" + truckReg +
                '}';
    }

}

package ru.shramko.logiweb.dao.entity;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="orders")
public class OrderEntity extends AbstractEntity {

    @Column(name = "number")
    @Getter @Setter
    private Integer number;

    @Column(name = "status")
    @Getter @Setter
    private Boolean status;

    @OneToOne
    @JoinColumn(name = "truck_id")
    @Getter @Setter
    private TruckEntity truck;

    @OneToMany (mappedBy="order_id")
    @Getter @Setter
    private List<DriverEntity> driverList;

    @OneToMany (mappedBy="order_id")
    @Getter @Setter
    private List<PointEntity> pointList;

    public OrderEntity() {
    }
}

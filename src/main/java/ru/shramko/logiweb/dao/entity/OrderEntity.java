package ru.shramko.logiweb.dao.entity;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="orders")
@Getter @Setter @NoArgsConstructor
public class OrderEntity extends AbstractEntity {

    @Column(name = "number")
    private Integer number;

    @Column(name = "status")
    private Boolean status;

    @OneToOne
    @JoinColumn(name = "truck_id")
    private TruckEntity truck;

    @OneToMany (mappedBy="order_id")
    private List<DriverEntity> driverList;

    @OneToMany (mappedBy="order_id")
    private List<PointEntity> pointList;

}

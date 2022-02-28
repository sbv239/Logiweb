package ru.shramko.logiweb.dao.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name ="drivers")
@Getter @Setter @NoArgsConstructor
public class DriverEntity extends AbstractEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "number")
    private Integer number;

    @Column(name = "hours")
    private Integer hours;

    @Column(name = "status")
    private String status;

    @OneToOne
    @JoinColumn(name = "city_id")
    private CityEntity city;

    @OneToOne
    @JoinColumn(name = "truck_id")
    private TruckEntity truckId;

    @ManyToOne
    @JoinColumn(name="order_id")
    private OrderEntity order_id;

}

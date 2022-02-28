package ru.shramko.logiweb.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "trucks")
public class TruckEntity extends AbstractEntity {

    @Column(name = "reg")
    @Getter @Setter
    private String reg;

    @Column(name = "shift")
    @Getter @Setter
    private Integer shift;

    @Column(name = "capacity")
    @Getter @Setter
    private Integer capacity;

    @Column(name = "state")
    @Getter @Setter
    private String state;

    @OneToOne
    @JoinColumn(name = "city_id")
    @Getter @Setter
    private CityEntity city;

    public TruckEntity() {
    }

}

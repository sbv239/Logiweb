package ru.shramko.logiweb.dao.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "cities")
@Getter @Setter @NoArgsConstructor
public class CityEntity extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "x_coord")
    private Integer xCoord;

    @Column(name = "y_coord")
    private Integer yCoord;

    @Override
    public String toString() {
        return name;
    }
}

package ru.shramko.logiweb.dao.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "cities")
@Data
public class City extends AbstractEntity {

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

package ru.shramko.logiweb.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="points")
public class PointEntity extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name="order_id")
    @Getter @Setter
    private OrderEntity order_id;

    public PointEntity() {
    }
}

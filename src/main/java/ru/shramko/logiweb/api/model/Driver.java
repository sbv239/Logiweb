package ru.shramko.logiweb.api.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.shramko.logiweb.dao.entity.City;

@Getter @Setter @NoArgsConstructor
public class Driver {

    private Integer id;
    private String firstName;
    private String lastName;
    private Integer number;
    private Integer hours;
    private String status;
    private City city;
//    private Truck truck;
//    private Order order;

}

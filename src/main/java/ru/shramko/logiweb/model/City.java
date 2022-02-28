package ru.shramko.logiweb.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class City {

    private Integer id;
    private String name;
    private Integer xCoord;
    private Integer yCoord;
    public String toString() {
        return name;
    }
}

package ru.shramko.logiweb.service.mapper;

import ru.shramko.logiweb.dao.entity.DriverEntity;
import ru.shramko.logiweb.api.model.Driver;

import java.util.ArrayList;
import java.util.List;

public class DriverMapper {

    public static DriverEntity toEntity(Driver driver) {

        DriverEntity driverEntity = new DriverEntity();

        driverEntity.setFirstName(driver.getFirstName());
        driverEntity.setLastName(driver.getLastName());
        driverEntity.setNumber(driver.getNumber());
        driverEntity.setStatus(driver.getStatus());
        driverEntity.setCity(driver.getCity());
        driverEntity.setHours(driver.getHours());

        return driverEntity;
    }

    public static List<Driver> toList(List<DriverEntity> driverEntityList) {

        List<Driver> driverList = new ArrayList<>();

        for (DriverEntity driverEntity : driverEntityList) {
            Driver driver = new Driver();

            driver.setFirstName(driverEntity.getFirstName());
            driver.setLastName(driverEntity.getLastName());
            driver.setCity(driverEntity.getCity());
            driver.setStatus(driverEntity.getStatus());
            driver.setHours(driverEntity.getHours());

            driverList.add(driver);
        }
        return  driverList;
    }
}

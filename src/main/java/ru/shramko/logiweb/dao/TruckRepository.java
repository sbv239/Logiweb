package ru.shramko.logiweb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shramko.logiweb.dao.entity.City;
import ru.shramko.logiweb.dao.entity.Truck;

import java.util.List;

public interface TruckRepository extends JpaRepository<Truck, Integer> {
    public List<Truck> findAllByCityAndStateAndOrderIsNullAndCapacityIsGreaterThan(City city, String state, Double weight);
}

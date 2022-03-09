package ru.shramko.logiweb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shramko.logiweb.dao.entity.City;
import ru.shramko.logiweb.dao.entity.Driver;

import java.util.List;

public interface DriverRepository extends JpaRepository<Driver, Integer> {

    public List<Driver> findAllByCityAndStatus(City city, String status);
}

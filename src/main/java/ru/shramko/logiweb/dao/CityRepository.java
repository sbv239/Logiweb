package ru.shramko.logiweb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shramko.logiweb.dao.entity.City;

public interface CityRepository extends JpaRepository<City, Integer> {
}

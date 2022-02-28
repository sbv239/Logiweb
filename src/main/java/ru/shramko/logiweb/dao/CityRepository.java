package ru.shramko.logiweb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shramko.logiweb.dao.entity.CityEntity;

public interface CityRepository extends JpaRepository<CityEntity, Integer> {
}

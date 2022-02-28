package ru.shramko.logiweb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shramko.logiweb.dao.entity.TruckEntity;

public interface TruckRepository extends JpaRepository<TruckEntity, Integer> {
}

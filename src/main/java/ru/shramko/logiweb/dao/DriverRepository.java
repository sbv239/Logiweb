package ru.shramko.logiweb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shramko.logiweb.dao.entity.DriverEntity;

public interface DriverRepository extends JpaRepository<DriverEntity, Integer> {
}

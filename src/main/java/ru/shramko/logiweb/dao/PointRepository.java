package ru.shramko.logiweb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shramko.logiweb.dao.entity.Point;

public interface PointRepository extends JpaRepository<Point, Integer> {
}

package ru.shramko.logiweb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.shramko.logiweb.dao.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>, CrudRepository<Order, Integer> {
}

package ru.shramko.logiweb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shramko.logiweb.dao.entity.Cargo;

import java.util.List;

public interface CargoRepository extends JpaRepository<Cargo, Integer> {

    public List<Cargo> findCargoEntityByStatusEquals(String status);
}

package ru.shramko.logiweb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shramko.logiweb.dao.entity.PersonEntity;

public interface PersonRepository extends JpaRepository<PersonEntity, Integer> {
    PersonEntity findByLogin(String login);
}

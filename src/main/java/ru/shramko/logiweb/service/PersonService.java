package ru.shramko.logiweb.service;

import org.springframework.stereotype.Service;
import ru.shramko.logiweb.dao.entity.PersonEntity;
import ru.shramko.logiweb.dao.PersonRepository;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public PersonEntity findByLogin(String login) {
        return personRepository.findByLogin(login);
    }
}

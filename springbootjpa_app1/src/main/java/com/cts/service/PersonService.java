package com.cts.service;

import com.cts.dao.PersonRepository;
import com.cts.exception.PersonException;
import com.cts.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
//@ComponentScan("com.cts.dao")
public class PersonService {

    @Autowired
    private PersonRepository repository;

    public Person addPerson(Person person) {
        return repository.save(person);
    }

    public Person searchPerson(int id) {
        Optional<Person> byId = repository.findById(id);
        if (byId.isPresent())
            return byId.get();
        else
            throw new PersonException("id not found");
    }

    public Person deletePersonById(int id) {
        Optional<Person> byId = repository.findById(id);
        Person p = null;
        if (byId.isPresent()) {
            p = byId.get();
            repository.delete(byId.get());
        }
        throw new PersonException("id not found");
    }

    public Person updatePersonById(Person p) {
        Optional<Person> byId = repository.findById(p.getPersonId());
        if (byId.isPresent()) {
            repository.save(p);
            return p;
        } else
            throw new PersonException("id not found");
    }

    public List<Person> getAllPerson() {
        if (repository.findAll().size() == 0)
            throw new PersonException("list is empty");
        else
            return repository.findAll();
    }
}

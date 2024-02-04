package com.kdu.smarthome.service;

import com.kdu.smarthome.repository.PersonRepository;
import com.kdu.smarthome.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {

    PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person createUser(String username) {
        Person newUser = new Person();
        newUser.setUsername(username);
        return personRepository.save(newUser);
    }

    public void addPerson(Person person) {
        personRepository.save(person);
    }

    public Person getPersonByName(String firstName) {
        return personRepository.getPersonByFirstName(firstName);
    }

    public Person getPersonByUsername(String username) throws Exception {
        Optional<Person> person = personRepository.getPersonByUsername(username);
        if (person.isPresent()) {
            return person.get();
        } else {
            throw new Exception("person not found");
        }
    }
}
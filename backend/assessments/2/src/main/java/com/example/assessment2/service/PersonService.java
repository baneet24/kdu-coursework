package com.example.assessment2.service;

import com.example.assessment2.dao.PersonDAO;
import com.example.assessment2.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    PersonDAO personDAO;

    @Autowired
    public PersonService(PersonDAO personDAO){
        this.personDAO = personDAO;
    }

    public void addPerson(Users person){
        personDAO.addPerson(person);
    }

    public Users getPersonById(int id){
        return personDAO.getPersonByIdx(id);
    }

    public String getRoleById(int id){
        return personDAO.getRoleByPersonIdx(id);
    }

    public Users getPersonUsername(String name){
        for(Users u : personDAO.getAllPersons()){
            if(u.getName().equals(name)){
                return u;
            }
        }
        return null;
    }
}

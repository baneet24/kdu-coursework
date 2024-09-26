package com.example.assessment2.dao;

import com.example.assessment2.model.Users;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class PersonDAO {
    List<Users> personList;

    public PersonDAO() {
        this.personList = new ArrayList<>();
    }

    public void addPerson(Users person){
        personList.add(person);
    }

    public Users getPersonByIdx(int index){
        return personList.get(index);
    }

    public String getRoleByPersonIdx(int index){
        return personList.get(index).getRole();
    }

    public List<Users> getAllPersons(){
        return personList;
    }
}

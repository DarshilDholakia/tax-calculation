package com.teamC.person;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonService {

    private PersonRepository personRepository;

    public List<Person> getAllPeople(){
        return personRepository.findAll();
    }

    public Optional<Person> getPersonById(int id){
        return personRepository.findById(id);
    }

    public Person addPerson(Person person){
        return personRepository.insert(person);
    }

    public void deletePersonById(int id){
        personRepository.deleteById(id);
    }

    public Person updatePersonById(int id, Person person){
        Optional<Person> existingPerson = personRepository.findById(id);
        if(existingPerson.isPresent()) {
            return personRepository.save(person);
        }
        else{
            throw new IllegalStateException("Person with id " + id + " doesn't exist");
        }
    }

}

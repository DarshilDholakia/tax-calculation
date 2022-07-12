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

    public Optional<Person> getPersonById(String id){
        return personRepository.findById(id);
    }

    public Person addPerson(Person person){
        return personRepository.insert(person);
    }

    public void deletePersonById(String id){
        personRepository.deleteById(id);
    }

    public Person updatePersonById(String id, Person person){
        Optional<Person> existingPerson = getPersonById(id);
        if (existingPerson.isPresent()) {
            Person updatePerson = existingPerson.get();
            updatePerson.setFirstName(person.getFirstName());
            updatePerson.setLastName(person.getLastName());
            updatePerson.setEmail(person.getEmail());
            updatePerson.setAge(person.getAge());
            updatePerson.setTaxNumber(person.getTaxNumber());
            personRepository.save(updatePerson);
            return updatePerson;
        } else{
            throw new IllegalStateException("Person with id " + id + " doesn't exist");
        }
    }


}

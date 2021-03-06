package com.teamC.person;

import com.teamC.calculation.exception.InvalidRequestException;
import com.teamC.clients.person.Person;
//import com.teamC.tax-calculation.exception.InvalidRequestException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class PersonService {

    private PersonRepository personRepository;

//    @Autowired
//    public void setPersonRepository(PersonRepository personRepository) {
//        this.personRepository = personRepository;
//    }

    public List<Person> getAllPeople(){
        return personRepository.findAll();
    }

    public Optional<Person> getPersonById(String id){
        validateId(id);
        return personRepository.findById(id);
    }

    public Person addPerson(Person person){
        validatePersonInputProperties(person);
        return personRepository.insert(person);
    }

    public void deletePersonById(String id){
        validateId(id);
        personRepository.deleteById(id);
    }

    public Person updatePersonById(String id, Person person){
        validateId(id);
        Optional<Person> existingPerson = getPersonById(id);
        validatePersonInputProperties(person);
        if (existingPerson.isPresent()) {
            Person updatePerson = existingPerson.get(); //updatePerson will have existingPerson's id
            updatePerson.setFirstName(person.getFirstName());
            updatePerson.setLastName(person.getLastName());
            updatePerson.setEmail(person.getEmail());
            updatePerson.setAge(person.getAge());
            updatePerson.setTaxNumber(person.getTaxNumber());
            personRepository.save(updatePerson); //as it has existingPerson's id, it'll replace it
            return updatePerson;
        } else{
            throw new IllegalStateException("Person with id " + id + " doesn't exist");
        }
    }

    public void validateId(String id){
        if (!(id.matches("[a-zA-Z0-9]*") && id.length()==24)){
            throw new InvalidRequestException("Id is not valid");
        };
    }

    public void validatePersonInputProperties(Person person){


        if (person.getFirstName().equals("")){
            throw new InvalidRequestException("First name cannot be blank");
        } else if (person.getLastName().equals("")){
            throw new InvalidRequestException("Last name cannot be blank");
        } else if (person.getEmail().equals("")) {
            throw new InvalidRequestException("Email cannot be blank");
        }
        String regex = "[\\w-]{1,20}@\\w{2,20}\\.\\w{2,3}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(person.getEmail());
        if (!matcher.matches()){
            throw new InvalidRequestException("Email is invalid");
        }
        else if (String.valueOf(person.getAge()).equals("")){
            throw new InvalidRequestException("Age cannot be blank");
        } else if ((person.getAge())<0){
            throw new InvalidRequestException("Age cannot be negative");
        } else if (!(String.valueOf(person.getAge()).matches("[0-9]+"))) {
            throw new InvalidRequestException("Age must be an integer");
        } else if (String.valueOf(person.getTaxNumber()).equals("")){
            throw new InvalidRequestException("Tax number cannot be blank");
        } else if ((person.getTaxNumber())<0) {
            throw new InvalidRequestException("Tax number cannot be negative");
        } else if (!(String.valueOf(person.getTaxNumber()).matches("[0-9]+"))){
            throw new InvalidRequestException("Tax number must be an integer");
        }
    }
}

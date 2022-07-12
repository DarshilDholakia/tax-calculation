package com.teamC.person;

import com.teamC.clients.Person;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("person")
public class PersonController {

    private PersonService personService;

    @GetMapping("all")
    public List<Person> getAllPeople(){
        return personService.getAllPeople();
    }

    @GetMapping("{id}")
    public Optional<Person> getPersonById(@PathVariable("id") String id){
        return personService.getPersonById(id);
    }

    @PostMapping
    public ResponseEntity<Person> addPerson(@RequestBody Person person){
        Person newPerson = personService.addPerson(person);
        return new ResponseEntity<>(newPerson, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public void deletePersonById(@PathVariable("id") String id){
        personService.deletePersonById(id);
    }

    @PutMapping("{id}")
    public Person updatePersonById(@PathVariable("id") String id, @RequestBody Person person){
        return personService.updatePersonById(id, person);
    }
}

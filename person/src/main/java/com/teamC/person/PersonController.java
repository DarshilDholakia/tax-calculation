package com.teamC.person;

import lombok.AllArgsConstructor;
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
    public Optional<Person> getPersonById(@PathVariable("id") int id){
        return personService.getPersonById(id);
    }

    @PostMapping
    public Person addPerson(@RequestBody Person person){
        return personService.addPerson(person);
    }

    @DeleteMapping("{id}")
    public void deletePersonById(@PathVariable("id") int id){
        personService.deletePersonById(id);
    }

    @PutMapping("{id}")
    public Person updatePersonById(@PathVariable("id") int id, @RequestBody Person person){
        return personService.updatePersonById(id, person);
    }
}

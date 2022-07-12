package com.teamC.clients.person;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient("person")
public interface PersonClient {

    @GetMapping("person/{id}")
    public Optional<Person> getPersonById(@PathVariable("id") String id);
}

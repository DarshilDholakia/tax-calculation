package com.teamC.clients.person;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Optional;

@FeignClient("person")
public interface PersonClient {

    @GetMapping("person/{id}")
    public Optional<Person> getPersonById(@PathVariable("id") String id);
}
//income service go through the personclient to access this method through person authorization , and this method will be in the person controller
//request need to be authenrized.
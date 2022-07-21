package com.teamC.clients.income;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient("income")
public interface IncomeClient {

    @GetMapping("incomes/person/{person_id}")
    public Income getIncomeByPersonId(@PathVariable("person_id") String id);
}

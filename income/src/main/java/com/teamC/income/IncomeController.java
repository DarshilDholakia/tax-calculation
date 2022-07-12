package com.teamC.income;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("incomes")
public class IncomeController {

    private IncomeService incomeService;

    @GetMapping("all")
    public List<Income> getAllIncome(){
        return incomeService.getAllIncome();
    }

    @GetMapping("personId/{id}")
    public List<Income> getAllIncomeByPersonId(@PathVariable("id") String id){
        return incomeService.getAllIncomeByPersonId(id);
    }

    @GetMapping("{id}")
    public Optional<Income> getIncomeById(@PathVariable("id") String id){
        return incomeService.getIncomeById(id);
    }

    @PostMapping
    public ResponseEntity<Income> addIncome(@RequestBody Income income){
        Income newIncome = incomeService.addIncome(income);
        return new ResponseEntity<>(newIncome, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public void deleteIncomeById(@PathVariable("id") String id){
        incomeService.deleteIncomeById(id);
    }

    @PutMapping("{id}")
    public Income updateIncomeById(@PathVariable("id") String id, @RequestBody Income income){
        return incomeService.updateIncomeById(id, income);
    }
}

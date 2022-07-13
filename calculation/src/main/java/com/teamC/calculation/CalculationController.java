package com.teamC.calculation;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CalculationController {
    private CalculationService calculationService;

    @PostMapping("tax-calculations/person/{id}")
    public ResponseEntity<String> sendPersonIdToCalculateTax(@PathVariable("id") String personId) {

        return new ResponseEntity<>(calculationService.pushPersonIdToQueue(personId), HttpStatus.ACCEPTED);
    }

    @GetMapping("tax-calculations/{id}")
    public ResponseEntity <Calculation> getPersonByCalculationId(@PathVariable("id")String calculationId){
        Calculation existingCalculation = calculationService.getCalculationByCalculationId(calculationId);
        if(existingCalculation.getTax()==null){
            return new ResponseEntity<>(existingCalculation,HttpStatus.PROCESSING);

        }else{
            return new ResponseEntity<>(existingCalculation,HttpStatus.ACCEPTED);
        }

    }

    @GetMapping("tax-calculations/all")
    public List<Calculation> getAllCalculation(){
        return calculationService.getAllCalculation();
    }







}

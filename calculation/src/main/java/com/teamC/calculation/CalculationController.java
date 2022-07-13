package com.teamC.calculation;

import com.teamC.calculation.exception.NotFoundException;
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
    public ResponseEntity <Calculation> getCalculationByCalculationId(@PathVariable("id")String calculationId){
        System.out.println("blah1");
        Calculation existingCalculation = calculationService.getCalculationByCalculationId(calculationId);
        System.out.println("blah1.5");
        if(existingCalculation.getTax()==null){
            System.out.println(existingCalculation.toString());
            System.out.println("blah2");
            ResponseEntity<Calculation> blah = new ResponseEntity<Calculation>(existingCalculation,HttpStatus.PROCESSING);
            System.out.println(blah.toString());
            return blah;

        }else{
            return new ResponseEntity<>(existingCalculation,HttpStatus.ACCEPTED);
        }
    }

    @GetMapping("tax-calculations/all")
    public List<Calculation> getAllCalculation(){
        return calculationService.getAllCalculation();
    }

    @GetMapping("tax-calculations")
    public List<Calculation> getTaxCalculations(){
        throw new NotFoundException("You need to include Calculation Id in the path");
    }

}

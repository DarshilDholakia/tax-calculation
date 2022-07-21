package com.teamC.calculation;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("tax-calculations")
public class CalculationController {
    private CalculationService calculationService;

    @PostMapping("person/{id}")
    public ResponseEntity<String> sendPersonIdToCalculateTax(@PathVariable("id") String personId) {
        return new ResponseEntity<>(calculationService.pushPayloadToQueue(personId), HttpStatus.ACCEPTED);
    }

//    @RequestHeader(value = "Authorization", required = true) String authorizationHeader,
    @GetMapping("{id}")
    public ResponseEntity <Calculation> getCalculationByCalculationId(@PathVariable("id")String calculationId){
        System.out.println("blah1");
        Calculation existingCalculation = calculationService.getCalculationByCalculationId(calculationId);
        System.out.println("blah1.5");
        if(existingCalculation.getTax()==null){
            System.out.println(existingCalculation.toString());
            System.out.println("blah2");
            ResponseEntity<Calculation> blah = new ResponseEntity<Calculation>(existingCalculation,HttpStatus.PARTIAL_CONTENT);
            System.out.println(blah.toString());
//            try {
//            TimeUnit.SECONDS.sleep(10); //delay by 10sec
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//            calculationService.calculateTaxAndPost(authorizationHeader, existingCalculation.getPersonId());
            return blah;
        }else{
            return new ResponseEntity<>(existingCalculation,HttpStatus.ACCEPTED);
        }
    }

    @GetMapping("all")
    public List<Calculation> getAllCalculation(){
        return calculationService.getAllCalculation();
    }

}

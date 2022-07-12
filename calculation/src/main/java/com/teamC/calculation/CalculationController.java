package com.teamC.calculation;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CalculationController {
    private CalculationService calculationService;

//    @PostMapping("tax-calculations/person/{id}")
//    public ResponseEntity<String> sendPersonIdToCalculateTax(@PathVariable("id") String personId) {
//        return new ResponseEntity<>(calculationService.pushPersonIdToQueue(personId), HttpStatus.ACCEPTED);
//    }
    @GetMapping("tax-calculations/person")
    public ResponseEntity<String> sendPersonIdToCalculateTax() {
        return new ResponseEntity<>(calculationService.pushPersonIdToQueue(), HttpStatus.ACCEPTED);
    }


}

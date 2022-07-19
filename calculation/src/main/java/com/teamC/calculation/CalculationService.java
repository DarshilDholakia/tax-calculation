package com.teamC.calculation;


import com.teamC.amqp.RabbitMQConfig;
import com.teamC.amqp.RabbitMQMessageProducer;
import com.teamC.calculation.exception.InvalidRequestException;
import com.teamC.calculation.exception.NotFoundException;
import com.teamC.clients.income.Income;
import com.teamC.clients.income.IncomeClient;
import com.teamC.clients.person.Person;
import com.teamC.clients.person.PersonClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class CalculationService {
    private CalculationRepository calculationRepository;
    private IncomeClient incomeClient;
    private RabbitMQMessageProducer rabbitMQMessageProducer;
    private RabbitMQConfig rabbitMQConfig;
    private PersonClient personClient;

    public Calculation getCalculationByPersonId(String personId) {
        validateId(personId);
        if (calculationRepository.findByPersonId(personId).isEmpty()) {
            throw new IllegalStateException("Calculation for person with ID: " + personId + " does not exist!");
        } else {
            System.out.println("calculation = " + calculationRepository.findByPersonId(personId).get());
            return calculationRepository.findByPersonId(personId).get();
        }
    }


    public Calculation getCalculationByCalculationId(String calculationId) {
        validateId(calculationId);
        Optional<Calculation> existingCalculation = calculationRepository.findById(calculationId);
        if (existingCalculation.isEmpty()) {
            throw new NotFoundException("Calculation ID: " + calculationId + " does not exist!");
        }
        return existingCalculation.get();
    }

    public String pushPayloadToQueue(String authorizationHeader, String personId) {
        validateId(personId);
        Optional<Person> existingPerson = personClient.getPersonById(authorizationHeader, personId);
        if (existingPerson.isPresent()) {
            Calculation nullCalculation = new Calculation();
            nullCalculation.setPersonId(personId);
            nullCalculation.setTax(null);
            Calculation insertedCalculation = calculationRepository.insert(nullCalculation);
            Payload payload = Payload.builder().authorizationnHeader(authorizationHeader).personId(personId).build();
            rabbitMQMessageProducer.publish(payload, rabbitMQConfig.getInternalExchange(), rabbitMQConfig.getInternalNotificationRoutingKey());
            log.info("Person ID: {} pushed to queue and the Calculation ID is: {}", personId, insertedCalculation.getId());
            return insertedCalculation.getId();
        } else {
            throw new IllegalStateException("Person with ID " + personId + " doesn't exist!");
        }
    }

    public Calculation calculateTaxAndPost(String authorizationHeader, String personId) {
        validateId(personId);
        Income income = incomeClient.getIncomeByPersonId(authorizationHeader, personId);
        Calculation existingCalculation = getCalculationByPersonId(personId);

        if (income != null) {
            double employmentIncome = income.getEmploymentIncome();
            double selfEmploymentIncome = income.getSelfEmploymentIncome();
            double capitalGains = income.getCapitalGains();
            double employmentIncomeSum = employmentIncome + selfEmploymentIncome;
            double remainingSum = employmentIncomeSum;
            double tax = 0;

            if (remainingSum > 162570) {
                tax += 0.45 * (remainingSum - 162570);
                remainingSum = 162570;
            }
            if (remainingSum > 50271) {
                tax += 0.4 * (remainingSum - 50271);
                remainingSum = 50271;
            }
            if (remainingSum > 12570) {
                tax += 0.2 * (remainingSum - 12570);
                remainingSum = 0;
            }

            if (capitalGains > 12300) {
                double capitalGainsAboveAllowance = capitalGains - 12300;
                if (capitalGainsAboveAllowance + employmentIncomeSum > 37700) {
                    tax += capitalGainsAboveAllowance * 0.2;
                } else {
                    tax += capitalGainsAboveAllowance * 0.1;
                }
            }
            existingCalculation.setTax(tax);
            calculationRepository.save(existingCalculation);
        } else {
            throw new IllegalStateException("No income for person with ID: " + personId + " !");
        }
        return existingCalculation;
    }

    public List<Calculation> getAllCalculation() {
        return calculationRepository.findAll();
    }

    public void validateId(String id){
        if (!(id.matches("[a-zA-Z0-9]*") && id.length()==24)){
            throw new InvalidRequestException("Id is not valid");
        };
    }

//    public Boolean validateId(String id) {
//        if (id.matches("[a-zA-Z0-9]*") && id.length()==24) {
//            return true;
//        } else throw new InvalidRequestException("Please enter a valid ID");
//    }
}

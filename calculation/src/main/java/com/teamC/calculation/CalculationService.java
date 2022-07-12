package com.teamC.calculation;

import com.teamC.amqp.RabbitMQConfig;
import com.teamC.amqp.RabbitMQMessageProducer;
import com.teamC.clients.income.IncomeClient;
import com.teamC.clients.person.Person;
import com.teamC.clients.person.PersonClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class CalculationService {
    private CalculationRepository calculationRepository;
//    private IncomeClient incomeClient;
    private RabbitMQMessageProducer rabbitMQMessageProducer;
    private RabbitMQConfig rabbitMQConfig;
    private PersonClient personClient;

    public String pushPersonIdToQueue(String personId) {
        Optional<Person> existingPerson = personClient.getPersonById(personId);
        if(existingPerson.isPresent()) {
            Calculation nullCalculation = new Calculation();
            nullCalculation.setPersonId(personId);
            nullCalculation.setTax(null);
            Calculation insertedCalculation = calculationRepository.insert(nullCalculation);
            rabbitMQMessageProducer.publish(personId, rabbitMQConfig.getInternalExchange(), rabbitMQConfig.getInternalNotificationRoutingKey());
            log.info("Person ID: {} pushed to queue and the Calculation ID is: {}", personId, insertedCalculation.getId());
            return insertedCalculation.getId();
        } else {
            throw new IllegalStateException("Person with ID " + personId + " doesn't exist!");
        }
    }


    //todo: use income client to fetch income data, calculate the tax, post using calculationRepository
    public Calculation calculateTaxAndPost(String personId) {

        return null;
    }
}

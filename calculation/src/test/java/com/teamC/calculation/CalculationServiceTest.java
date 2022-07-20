package com.teamC.calculation;

import com.teamC.amqp.RabbitMQConfig;
import com.teamC.amqp.RabbitMQMessageProducer;
import com.teamC.clients.income.Income;
import com.teamC.clients.income.IncomeClient;
import com.teamC.clients.person.Person;
import com.teamC.clients.person.PersonClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class CalculationServiceTest {

    private CalculationService underTest;
    private CalculationRepository calculationRepository;
    private IncomeClient incomeClient;
    private PersonClient personClient;
    private RabbitMQConfig rabbitMQConfig;
    private RabbitMQMessageProducer rabbitMQMessageProducer;



    @BeforeEach
    void setUp() {
        calculationRepository = mock(CalculationRepository.class);
        underTest = new CalculationService(calculationRepository, incomeClient, rabbitMQMessageProducer, rabbitMQConfig, personClient);
        incomeClient = mock(IncomeClient.class);
        personClient = mock(PersonClient.class);
        rabbitMQConfig = mock(RabbitMQConfig.class);
        rabbitMQMessageProducer = mock(RabbitMQMessageProducer.class);
    }

    @Test
    void exceptionThrownWhenInvalidIdEnteredForGetCalculationByPersonId() {
        //GIVEN
        String personId = "1";
        //THEN
        assertThatThrownBy(() -> {
            //WHEN
            underTest.getCalculationByPersonId(personId);
        }).hasMessage("Id is not valid");


    }

    @Test
    void correctExceptionThrownForGetCalculationByPersonId() {
        //GIVEN
        String personId = "62d14f220bd8dd05b9237681";
        given(calculationRepository.findByPersonId(personId)).willReturn(Optional.empty());
        //THEN
        assertThatThrownBy(() -> {
            //WHEN
            underTest.getCalculationByPersonId(personId);
        }).hasMessage("Calculation for person with ID: " + personId + " does not exist!");
    }

    @Test
    void correctPersonFoundForGetCalculationByPersonId() {
        //GIVEN
        String personId = "62d14f220bd8dd05b9237681";
        String calculationId = "62d1595dadbe3e3ab9f9ae21";
        Calculation calculation = new Calculation(calculationId, personId, 10000.00);
        given(calculationRepository.findByPersonId(personId)).willReturn(Optional.of(calculation));
        //WHEN
        Calculation result = underTest.getCalculationByPersonId(personId);
        //THEN
        assertThat(result).isEqualTo(calculation);
    }

    @Test
    void throwExceptionWhenCalcDoesntExistForGetCalculationByCalculationId() {
        //GIVEN
        String calculationId = "62d1595dadbe3e3ab9f9ae21";
        given(calculationRepository.findById(calculationId)).willReturn(Optional.empty());
        //THEN
        assertThatThrownBy(() -> {
            //WHEN
            underTest.getCalculationByCalculationId(calculationId);
        }).hasMessage("Calculation ID: " + calculationId + " does not exist!");
    }

    @Test
    void getCorrectCalcForGetCalculationByCalculationId() {
        //GIVEN
        String personId = "62d14f220bd8dd05b9237681";
        String calculationId = "62d1595dadbe3e3ab9f9ae21";
        Calculation calculation = new Calculation(calculationId, personId, 10000.00);
        given(calculationRepository.findById(calculationId)).willReturn(Optional.of(calculation));
        //WHEN
        Calculation result = underTest.getCalculationByCalculationId(calculationId);
        //THEN
        assertThat(result).isEqualTo(calculation);
    }

    @Test
    void throwExceptionWhenNoPersonExistsForPushPayloadToQueue() {
        //GIVEN
        String authorizationHeader = "authorizationHeader";
        String personId = "62d14f220bd8dd05b9237681";
        given(personClient.getPersonById(authorizationHeader, personId)).willReturn(Optional.empty());
        //THEN
        assertThatThrownBy(() -> {
            //WHEN
            underTest.pushPayloadToQueue(authorizationHeader, personId);
        }).hasMessage("Person with ID " + personId + " doesn't exist!");
    }

    @Disabled
    @Test
    void correctCalcIdReturnedForPushPayloadToQueue() {
    }

    @Test
    void throwExceptionWhenNoIncomeFoundForCalculateTaxAndPost() {
        //GIVEN
        String authorizationHeader = "authorizationHeader";
        String personId = "62d14f220bd8dd05b9237681";
        String calculationId = "62d1595dadbe3e3ab9f9ae21";
        Calculation nullCalc = new Calculation(calculationId, personId, null);
        given(incomeClient.getIncomeByPersonId(authorizationHeader, personId)).willReturn(null);
        doReturn(Optional.of(nullCalc)).when(calculationRepository).findByPersonId(personId);
        given(underTest.getCalculationByPersonId(personId)).willReturn(nullCalc);
        //THEN
        assertThatThrownBy(() -> {
            //WHEN
            underTest.calculateTaxAndPost(authorizationHeader, personId);
        }).hasMessage("No income for person with ID: " + personId + " !");
    }

    @Test
    void returnCorrectCalculationObjectForCalculateTaxAndPost() {
        //GIVEN
        String authorizationHeader = "authorizationHeader";
        String personId = "62d14f220bd8dd05b9237681";
        String calculationId = "62d1595dadbe3e3ab9f9ae21";
        Income income = new Income(200000, 0, 0);
        Calculation nullCalc = new Calculation(calculationId, personId, null);
        Calculation finalCalc = new Calculation(calculationId, personId, 69303.30);
        given(incomeClient.getIncomeByPersonId(authorizationHeader, personId)).willReturn(income);
        given(underTest.getCalculationByPersonId(personId)).willReturn(nullCalc);
        given(calculationRepository.save(finalCalc)).willReturn(finalCalc);
        //WHEN
        Calculation result = underTest.calculateTaxAndPost(authorizationHeader, personId);
        //THEN
        assertThat(result).isEqualTo(finalCalc);
    }

    @Disabled
    @Test
    void getAllCalculation() {
    }
}
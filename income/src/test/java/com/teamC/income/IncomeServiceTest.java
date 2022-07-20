package com.teamC.income;

import com.teamC.clients.income.Income;
import com.teamC.clients.person.Person;
import com.teamC.clients.person.PersonClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

class IncomeServiceTest {
    private IncomeRepository incomeRepository;
    private IncomeService underTest;
    private PersonClient personClient;


    @BeforeEach
    void setUp() {

        incomeRepository = Mockito.mock(IncomeRepository.class);
        personClient = Mockito.mock(PersonClient.class);
        underTest = new IncomeService(incomeRepository,personClient);


    }

    @Test
    void getAllIncome() {
        ///given
        Income income1 = new Income("1","2",20000,30000,500);
        Income income2 = new Income("2","3",10000,40000,0);
        List<Income> expectedAllIncomeList= Arrays.asList(income1,income2);
        given(incomeRepository.findAll()).willReturn(expectedAllIncomeList);
        //when
        List<Income> actualAllIncomeList=underTest.getAllIncome();

        //then
        assertThat(expectedAllIncomeList).isEqualTo(actualAllIncomeList);



    }

    @Test
    void getIncomeById() {

    }

    @Test
    void getIncomeByPersonId() {
    }

    @Test
    void addIncome() {
    }

    @Test
    void deleteIncomeById() {
    }

    @Test
    void updateIncomeById() {
    }

    @Test
    void testGetAllIncome() {
    }

    @Test
    void testGetIncomeById() {
    }

    @Test
    void testGetIncomeByPersonId() {
    }

    @Test
    void testAddIncome() {
    }

    @Test
    void testDeleteIncomeById() {
    }

    @Test
    void testUpdateIncomeById() {
    }
}
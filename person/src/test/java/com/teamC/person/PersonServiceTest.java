package com.teamC.person;

import com.teamC.clients.person.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.InstanceOfAssertFactories.LIST;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import static org.junit.jupiter.api.Assertions.*;

class PersonServiceTest {

    private PersonRepository personRepository;
    private PersonService underTest;
    @BeforeEach
    void setUp() {
        personRepository = Mockito.mock(PersonRepository.class);
        underTest = new PersonService(personRepository);
    }

    @Test
    void getAllPeople() {
        //given
        Person person1 = new Person("1", "John", "Lewis", "johnL@gmail,com", 21, 6565865L);
        Person person2 = new Person("2", "John2", "Lewis2", "john2L@gmail,com", 22, 7565865L);
        List<Person> expectedAllPeopleList = Arrays.asList(person1, person2);

        given(personRepository.findAll()).willReturn(expectedAllPeopleList);

        //when
        List<Person> actualAllPeopleList = underTest.getAllPeople();

        //then
        assertThat(expectedAllPeopleList).isEqualTo(actualAllPeopleList);
    }

    @Test
    void getPersonById() {

    }

    @Test
    void addPerson() {
    }

    @Test
    void deletePersonById() {
    }

    @Test
    void updatePersonById() {
    }
}
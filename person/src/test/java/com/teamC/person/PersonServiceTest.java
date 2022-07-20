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
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.InstanceOfAssertFactories.LIST;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
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
        Person person1 = new Person("1", "John", "Lewis", "johnL@gmail.com", 21, 6565865L);
        Person person2 = new Person("2", "John2", "Lewis2", "john2L@gmail.com", 22, 7565865L);
        List<Person> expectedAllPeopleList = Arrays.asList(person1, person2);
        given(personRepository.findAll()).willReturn(expectedAllPeopleList);

        //when
        List<Person> result = underTest.getAllPeople();

        //then
        assertThat(expectedAllPeopleList).isEqualTo(result);
    }

    @Test
    void getPersonById() {
        //given
        String personId = "62d54d09057f4e1f5068e8ec";
        Person person = new Person(personId, "John", "Lewis", "johnL@gmail.com", 21, 6565865L);
        given(personRepository.findById(personId)).willReturn(Optional.of(person));

        //when
        Optional<Person> result = underTest.getPersonById(personId);

        //then
        assertThat(Optional.of(person)).isEqualTo(result);
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

    @Test
    void acceptsValidPersonIdThatIsAMixOfNumbersAndLetters() {
        //given
        String personId = "62d54d09057f4e1f5068e8ec";

        //when/then
        assertDoesNotThrow(() -> {
            underTest.validateId(personId);
        });
    }

    @Test
    void acceptsValidPersonIdThatIsJustNumbers() {
        //given
        String personId = "666666666666666666666666";

        //when/then
        assertDoesNotThrow(() -> {
            underTest.validateId(personId);
        });
    }

    @Test
    void acceptsValidPersonIdThatIsJustLetters() {
        //given
        String personId = "ffffffffffffffffffffffff";

        //when/then
        assertDoesNotThrow(() -> {
            underTest.validateId(personId);
        });
    }

    @Test
    void rejectsPersonIdLessThan24Characters() {
        //given
        String personId = "62d54d09057f4e1f5068e8e";

        //when/then
        assertThatThrownBy(() -> {
            underTest.validateId(personId);
        }).hasMessage("Id is not valid");
    }

    @Test
    void rejectsPersonIdMoreThan24Characters() {
        //given
        String personId = "62d54d09057f4e1f5068e8eff";

        //when/then
        assertThatThrownBy(() -> {
            underTest.validateId(personId);
        }).hasMessage("Id is not valid");
    }

    @Test
    void rejectsPersonIdWithSpecialCharacters() {
        //given
        String personId = "$2d54d09057f4e1f5068e8ec";

        //when/then
        assertThatThrownBy(() -> {
            underTest.validateId(personId);
        }).hasMessage("Id is not valid");
    }

    @Test
    void validatesPersonWithValidInputProperties() {
        //given
        Person person = new Person("62d54d09057f4e1f5068e8ec", "John", "Lewis", "johnL@gmail.com", 21, 6565865L);

        //when/then
        assertDoesNotThrow(() -> {
            underTest.validatePersonInputProperties(person);
        });
    }

    @Test
    void invalidatesPersonWithBlankFirstName() {
        //given
        String firstName = "";
        Person person = new Person("62d54d09057f4e1f5068e8ec", firstName, "Lewis", "johnL@gmail.com", 21, 6565865L);

        //when/then
        assertThatThrownBy(() -> {
            underTest.validatePersonInputProperties(person);
        }).hasMessage("First name cannot be blank");
    }

    @Test
    void invalidatesPersonWithBlankLastName() {
        //given
        String lastName = "";
        Person person = new Person("62d54d09057f4e1f5068e8ec", "John", lastName, "johnL@gmail.com", 21, 6565865L);

        //when/then
        assertThatThrownBy(() -> {
            underTest.validatePersonInputProperties(person);
        }).hasMessage("Last name cannot be blank");
    }

    @Test
    void invalidatesPersonWithBlankEmail() {
        //given
        String email = "";
        Person person = new Person("62d54d09057f4e1f5068e8ec", "John", "Lewis", email, 21, 6565865L);

        //when/then
        assertThatThrownBy(() -> {
            underTest.validatePersonInputProperties(person);
        }).hasMessage("Email cannot be blank");
    }

    @Test
    void invalidatesPersonWithInvalidEmail() {
        //given
        String email = "blah";
        Person person = new Person("62d54d09057f4e1f5068e8ec", "John", "Lewis", email, 21, 6565865L);

        //when/then
        assertThatThrownBy(() -> {
            underTest.validatePersonInputProperties(person);
        }).hasMessage("Email is invalid");
    }

    @Test
    void validatesPersonWithValidEmail() {
        //given
        String email = "blah@hotmail.co.uk";
        Person person = new Person("62d54d09057f4e1f5068e8ec", "John", "Lewis", email, 21, 6565865L);

        //when/then
        assertDoesNotThrow(() -> {
            underTest.validatePersonInputProperties(person);
        });
    }

//    @Test
//    void invalidatesPersonWithNegativeAge() {
//        //given
//        int age = -1;
//        Person person = new Person("62d54d09057f4e1f5068e8ec", "John", "Lewis", "johnL@gmail.com", age, 6565865L);
//
//        //when/then
//        assertThatThrownBy(() -> {
//            underTest.validatePersonInputProperties(person);
//        }).hasMessage("Age cannot be negative");
//    }
//
//    @Test
//    void invalidatesPersonWithNegativeTaxNumber() {
//        //given
//        int taxNumber = -1;
//        Person person = new Person("62d54d09057f4e1f5068e8ec", "John", "Lewis", "johnL@gmail.com", 21, taxNumber);
//
//        //when/then
//        assertThatThrownBy(() -> {
//            underTest.validatePersonInputProperties(person);
//        }).hasMessage("Tax number cannot be negative");
//    }

}
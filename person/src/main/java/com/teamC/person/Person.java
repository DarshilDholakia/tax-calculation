package com.teamC.person;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Data //does getters and setters, toString etc for you
@AllArgsConstructor //creates a constructor with all arguments
@NoArgsConstructor
@Document //enables us to specify that this class will be a collection (tables in mongoDb)
// Documents (data entries/rows) are stored in a collection
public class Person {

    @Id
    private int id;
    private String firstName;
    private String lastName;
    @Indexed(unique = true)
    private String email;
    private int age;
    private Long taxNumber;

    public Person(String firstName, String lastName, String email, int age, Long taxNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.taxNumber = taxNumber;
    }
}

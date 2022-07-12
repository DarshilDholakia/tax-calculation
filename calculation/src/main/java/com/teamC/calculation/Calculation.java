package com.teamC.calculation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data //does getters and setters, toString etc for you
@AllArgsConstructor //creates a constructor with all arguments
@NoArgsConstructor
@Document
public class Calculation {
    @Id
    private String id;
    private String personId;
    private Double tax;

    public Calculation(String personId, Double tax) {
        this.personId = personId;
        this.tax = tax;
    }
}

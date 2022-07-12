package com.teamC.income;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data //does getters and setters, toString etc for you
@AllArgsConstructor //creates a constructor with all arguments
@NoArgsConstructor
@Document
public class Income {
    @Id
    private String id;
    private String personId;
    private double selfEmploymentIncome;
    private double employmentIncome;
    private double capitalGains;

    public Income(String personId, double selfEmploymentIncome, double employmentIncome, double capitalGains) {
        this.personId = personId;
        this.selfEmploymentIncome = selfEmploymentIncome;
        this.employmentIncome = employmentIncome;
        this.capitalGains = capitalGains;
    }


}

package com.teamC.income;

import com.teamC.clients.income.Income;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeRepository extends MongoRepository<Income, String> {
    Income findByPersonId(String personId);

}

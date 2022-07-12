package com.teamC.income;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeRepository extends MongoRepository<Income, String> {
    List<Income> findAllByPersonId(String personId);

}

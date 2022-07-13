package com.teamC.calculation;

import com.teamC.clients.income.Income;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CalculationRepository extends MongoRepository<Calculation, String> {

    Optional<Calculation> findByPersonId(String personId);
}

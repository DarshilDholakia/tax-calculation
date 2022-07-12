package com.teamC.income;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class IncomeService {

    private IncomeRepository incomeRepository;

    public List<Income> getAllIncome(){
        return incomeRepository.findAll();
    }

    public Optional<Income> getIncomeById(String id){
        return incomeRepository.findById(id);
    }

    public List<Income> getAllIncomeByPersonId(String personId){
        return incomeRepository.findAllByPersonId(personId);
    }

    public Income addIncome(Income income){
        return incomeRepository.insert(income);
    }

    public void deleteIncomeById(String id){
        incomeRepository.deleteById(id);
    }

    public Income updateIncomeById(String id, Income income){
        Optional<Income> existingIncome = getIncomeById(id);
        if (existingIncome.isPresent()) {
            Income updateIncome = existingIncome.get(); //updateIncome will have existingIncome's id
            updateIncome.setPersonId(income.getPersonId());
            updateIncome.setSelfEmploymentIncome(income.getSelfEmploymentIncome());
            updateIncome.setEmploymentIncome(income.getEmploymentIncome());
            updateIncome.setCapitalGains(income.getCapitalGains());
            incomeRepository.save(updateIncome); //as it has existingIncome's id, it'll replace it
            return updateIncome;
        } else{
            throw new IllegalStateException("Income with id " + id + " doesn't exist");
        }
    }


}

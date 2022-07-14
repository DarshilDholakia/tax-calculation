package com.teamC.income;

import com.teamC.clients.income.Income;
import com.teamC.clients.person.Person;
import com.teamC.clients.person.PersonClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class IncomeService {

    private IncomeRepository incomeRepository;

    private PersonClient personClient;

    public List<Income> getAllIncome(){
        return incomeRepository.findAll();
    }

    public Optional<Income> getIncomeById(String id){
        return incomeRepository.findById(id);
    }

    public Income getIncomeByPersonId(String authorizationHeader, String personId){
        Optional<Person> existingPerson = personClient.getPersonById(authorizationHeader, personId);
        if (existingPerson.isPresent()){
            return incomeRepository.findByPersonId(personId);
        }
        else{
            throw new IllegalStateException("Person with id " + personId + " doesn't exist");
        }
    }

    public Income addIncome(String authorizationHeader, String personId, Income income){
        Optional<Person> existingPerson = personClient.getPersonById(authorizationHeader, personId);
        if (existingPerson.isPresent()){
            Income newIncome = new Income();
            newIncome.setPersonId(personId);
            newIncome.setEmploymentIncome(income.getEmploymentIncome());
            newIncome.setSelfEmploymentIncome(income.getSelfEmploymentIncome());
            newIncome.setCapitalGains(income.getCapitalGains());
            return incomeRepository.insert(newIncome);
        }
        else{
            throw new IllegalStateException("Person with id " + personId + " doesn't exist");
        }
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

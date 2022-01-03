package com.crud.masterfinanceira.api.backend.service;

import java.util.Optional;

import com.crud.masterfinanceira.api.backend.model.Person;
import com.crud.masterfinanceira.api.backend.repository.PersonRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PersonService {
    
    private PersonRepository personRepository;

    public Person update(Long id, Person personUpdate) {
        Person personSave = getPersonId(id); 

        BeanUtils.copyProperties(personUpdate, personSave, "id");
        return personRepository.save(personSave);
        
    }

    private Person getPersonId(Long id) {
        Optional<Person> personSave = personRepository.findById(id);
        if (personSave.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        }
        return personSave.get();
    }

    public void updatePropertyActive(Long id, Boolean active) {
        Person personSave = getPersonId(id); 
        personSave.setActive(active);
        personRepository.save(personSave);
    }
}

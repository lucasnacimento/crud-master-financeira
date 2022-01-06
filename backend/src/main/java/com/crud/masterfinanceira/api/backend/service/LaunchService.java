package com.crud.masterfinanceira.api.backend.service;

import java.util.Optional;

import com.crud.masterfinanceira.api.backend.model.Launch;
import com.crud.masterfinanceira.api.backend.model.Person;
import com.crud.masterfinanceira.api.backend.repository.LaunchRepository;
import com.crud.masterfinanceira.api.backend.repository.PersonRepository;
import com.crud.masterfinanceira.api.backend.service.exception.PersonDoesNotExist;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LaunchService {
    
    private LaunchRepository launchRepository;

    private PersonRepository personRepository;

    public Launch save(Launch launch) {
        Optional<Person> person = personRepository.findById((launch.getPerson().getId())); 
        if (person.isEmpty() || !person.get().getActive()) {
            throw new PersonDoesNotExist();
        }
        return launchRepository.save(launch);
    }


}

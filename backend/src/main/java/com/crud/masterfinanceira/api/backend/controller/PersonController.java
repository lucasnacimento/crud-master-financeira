package com.crud.masterfinanceira.api.backend.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.crud.masterfinanceira.api.backend.event.ResourceEvent;
import com.crud.masterfinanceira.api.backend.model.Person;
import com.crud.masterfinanceira.api.backend.repository.PersonRepository;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/persons")
@AllArgsConstructor
public class PersonController {
    
    private PersonRepository personRepository;

    private ApplicationEventPublisher publisher;

    @GetMapping
    public List<Person> listAllPersons() {
        return personRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Person> savePerson(@Valid @RequestBody Person person, HttpServletResponse response) {
        Person personSave = personRepository.save(person);

       publisher.publishEvent(new ResourceEvent(this, response, personSave.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(personSave);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable Long id) {
        Optional<Person> person = personRepository.findById(id);
        return person.isPresent() ? ResponseEntity.ok(person.get()) : ResponseEntity.notFound().build();
    }

}

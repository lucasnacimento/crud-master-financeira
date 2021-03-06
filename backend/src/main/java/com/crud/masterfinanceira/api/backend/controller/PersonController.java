package com.crud.masterfinanceira.api.backend.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.crud.masterfinanceira.api.backend.event.ResourceEvent;
import com.crud.masterfinanceira.api.backend.model.Person;
import com.crud.masterfinanceira.api.backend.repository.PersonRepository;
import com.crud.masterfinanceira.api.backend.service.PersonService;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/persons")
@AllArgsConstructor
public class PersonController {
    
    private PersonRepository personRepository;

    private ApplicationEventPublisher publisher;

    private PersonService personService;

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
    
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deletePerson(@PathVariable Long id) {
        personRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @Valid @RequestBody Person person){
        Person personSave = personService.update(id, person);
        return ResponseEntity.ok(personSave);
    }

    @PutMapping("/{id}/active")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void updatePropertyActive(@PathVariable Long id, @RequestBody Boolean active){
        personService.updatePropertyActive(id, active);
    }

}

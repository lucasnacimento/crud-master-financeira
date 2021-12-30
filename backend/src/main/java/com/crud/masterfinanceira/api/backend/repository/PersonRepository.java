package com.crud.masterfinanceira.api.backend.repository;

import com.crud.masterfinanceira.api.backend.model.Person;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long>{
    
}

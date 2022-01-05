package com.crud.masterfinanceira.api.backend.repository;

import com.crud.masterfinanceira.api.backend.model.Launch;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LaunchRepository extends JpaRepository<Launch, Long>{
    
}

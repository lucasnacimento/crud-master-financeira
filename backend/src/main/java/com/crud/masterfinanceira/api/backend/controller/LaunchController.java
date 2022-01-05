package com.crud.masterfinanceira.api.backend.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.crud.masterfinanceira.api.backend.event.ResourceEvent;
import com.crud.masterfinanceira.api.backend.model.Launch;
import com.crud.masterfinanceira.api.backend.repository.LaunchRepository;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/launchs")
@AllArgsConstructor
public class LaunchController {
    
    private LaunchRepository launchRepository;
    
    private ApplicationEventPublisher publisher;
    
    @GetMapping
    public List<Launch> listAllLaunch() {
        return launchRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Launch> getIdLaunch(@PathVariable Long id) {
        Optional<Launch> launch = launchRepository.findById(id);
        return launch.isPresent() ? ResponseEntity.ok(launch.get()) : ResponseEntity.notFound().build();
    } 


    @PostMapping
    public ResponseEntity<Launch> saveLaunch(@Valid @RequestBody Launch launch, HttpServletResponse response){
        Launch launchSave = launchRepository.save(launch);
        publisher.publishEvent(new ResourceEvent(this, response, launchSave.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(launchSave);
    }
}

package com.crud.masterfinanceira.api.backend.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.crud.masterfinanceira.api.backend.event.ResourceEvent;
import com.crud.masterfinanceira.api.backend.exceptionHandler.MasterExceptionHandler.Erro;
import com.crud.masterfinanceira.api.backend.model.Launch;
import com.crud.masterfinanceira.api.backend.repository.LaunchRepository;
import com.crud.masterfinanceira.api.backend.repository.filter.LaunchFilter;
import com.crud.masterfinanceira.api.backend.service.LaunchService;
import com.crud.masterfinanceira.api.backend.service.exception.PersonDoesNotExist;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/launchs")
@AllArgsConstructor
public class LaunchController {
    
    private LaunchRepository launchRepository;

    private LaunchService launchService;
    
    private ApplicationEventPublisher publisher;

    private MessageSource msgSource;
    
    @GetMapping
    public List<Launch> listAllLaunch(LaunchFilter launchFilter) {
        return launchRepository.filter(launchFilter);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Launch> getIdLaunch(@PathVariable Long id) {
        Optional<Launch> launch = launchRepository.findById(id);
        return launch.isPresent() ? ResponseEntity.ok(launch.get()) : ResponseEntity.notFound().build();
    } 

    @PostMapping
    public ResponseEntity<Launch> saveLaunch(@Valid @RequestBody Launch launch, HttpServletResponse response){
        Launch launchSave = launchService.save(launch);
        publisher.publishEvent(new ResourceEvent(this, response, launchSave.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(launchSave);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteLaunch(@PathVariable Long id) {
        launchRepository.deleteById(id);
    }

    @ExceptionHandler({ PersonDoesNotExist.class })
    public ResponseEntity<Object> handlePersonDoesNotExist(PersonDoesNotExist ex){
        String messageUser = msgSource.getMessage("person.not.exist.or.inative", null, LocaleContextHolder.getLocale());
        String messageDev = ex.toString();
        List<Erro> errs =Arrays.asList(new Erro(messageUser, messageDev));
        return ResponseEntity.badRequest().body(errs);
    }
}

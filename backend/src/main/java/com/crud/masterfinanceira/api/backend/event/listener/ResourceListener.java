package com.crud.masterfinanceira.api.backend.event.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import com.crud.masterfinanceira.api.backend.event.ResourceEvent;

import org.springframework.context.ApplicationListener;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class ResourceListener implements ApplicationListener<ResourceEvent> {

    @Override
    public void onApplicationEvent(ResourceEvent resourceCreateEvent) {
        HttpServletResponse response = resourceCreateEvent.getResponse();
        Long code = resourceCreateEvent.getCode();

        addHeaderLocation(response, code);
    }

    private void addHeaderLocation(HttpServletResponse response, Long code){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
        .buildAndExpand(code).toUri();

        response.setHeader("Location", uri.toASCIIString());
    }
    
}

package com.crud.masterfinanceira.api.backend.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;

@Getter
public class ResourceEvent extends ApplicationEvent{

    private static final long serialVersionUID = 1L;

    private HttpServletResponse response;
    private Long code;

    public ResourceEvent(Object source, HttpServletResponse response, Long code) {
        super(source);
        this.response = response;
        this.code = code;
    }
    
}

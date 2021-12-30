package com.crud.masterfinanceira.api.backend.exceptionHandler;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;

@ControllerAdvice
@AllArgsConstructor
public class ExceptionHandler extends ResponseEntityExceptionHandler{
    
    private MessageSource msgSource;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
            
            String messageUser = msgSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
            String messageDev = ex.getCause().toString();
            return handleExceptionInternal(ex, new Erro(messageUser, messageDev), headers, HttpStatus.BAD_REQUEST, request);
    }

        @AllArgsConstructor
        @Getter
        public static  class Erro {

            private String messageUser;
            private String messageDev;

        }
}

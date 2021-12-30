package com.crud.masterfinanceira.api.backend.exceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
            List<Erro> errs =Arrays.asList(new Erro(messageUser, messageDev));
            return handleExceptionInternal(ex, errs , headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
            
                List<Erro> errs = createListError(ex.getBindingResult());
            return handleExceptionInternal(ex, errs, headers, HttpStatus.BAD_REQUEST, request);

    }

    private List<Erro> createListError(BindingResult bindingResult) {
        List<Erro> errs = new ArrayList<>();

        for (FieldError er : bindingResult.getFieldErrors()) {
            String messageUser = msgSource.getMessage(er, LocaleContextHolder.getLocale());
            String messageDev = er.toString();
            errs.add(new Erro(messageUser, messageDev));
        }

        return errs;
    }

        @AllArgsConstructor
        @Getter
        public static  class Erro {

            private String messageUser;
            private String messageDev;

        }
}

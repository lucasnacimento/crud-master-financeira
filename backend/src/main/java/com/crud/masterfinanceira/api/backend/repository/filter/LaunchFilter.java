package com.crud.masterfinanceira.api.backend.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class LaunchFilter {

    private String description;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate dateLoserFrom;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate dateLoserTo;
}

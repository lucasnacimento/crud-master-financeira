package com.crud.masterfinanceira.api.backend.model;

import lombok.Getter;

@Getter
public enum LaunchType {
    
     REVENUE("RECEITA"), EXPENSE("DESPESA");

    private String status;

     LaunchType(String status) {
        this.status = status;
     }
}

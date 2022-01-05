package com.crud.masterfinanceira.api.backend.model;

import javax.persistence.Embeddable;

import lombok.Getter;

@Getter
public enum LaunchType {
    
     RECEITA, DESPESA;
}

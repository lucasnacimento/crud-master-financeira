package com.crud.masterfinanceira.api.backend.model;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address {
    
    private String street;
    private String number;
    private String complement;
    private String district;
    private String zipcode;
    private String city;
    private String state;
}

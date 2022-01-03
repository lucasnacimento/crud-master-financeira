package com.crud.masterfinanceira.api.backend.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Launch {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Column(name = "date_loser")
    private LocalDate dateLoser;
    
    @Column(name = "date_payment")
    private LocalDate datePayment;

    private BigDecimal price;

    private String note;

    @Enumerated(EnumType.STRING)
    private LaunchType launchType;

    @ManyToOne
    @JoinColumn(name = "id_person")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;

}

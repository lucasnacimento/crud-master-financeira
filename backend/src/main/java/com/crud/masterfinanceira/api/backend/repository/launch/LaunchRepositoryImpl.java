package com.crud.masterfinanceira.api.backend.repository.launch;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.crud.masterfinanceira.api.backend.model.Launch;
import com.crud.masterfinanceira.api.backend.repository.filter.LaunchFilter;

public class LaunchRepositoryImpl implements LaunchRepositoryQuery{

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Launch> filter(LaunchFilter launchFilter) {
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Launch.class);

        Root<Launch> root = criteriaQuery.from(Launch.class);

        Predicate[] predicates = createRestrictions(launchFilter, criteriaBuilder, root);
        criteriaQuery.where(predicates);

        TypedQuery<Launch> query = manager.createQuery(criteriaQuery);

        return query.getResultList();

    }

    private Predicate[] createRestrictions(LaunchFilter launchFilter, CriteriaBuilder criteriaBuilder, Root<Launch> root) {
        List<Predicate> predicates = new ArrayList<>();
       
        if (launchFilter.getDescription() != null) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), "%" + launchFilter.getDescription().toLowerCase() + "%"));
        } 
        if (launchFilter.getDateLoserFrom() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("dateLoser"), launchFilter.getDateLoserFrom()));
        }
        if (launchFilter.getDateLoserTo() != null) {
           predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("dateLoser"), launchFilter.getDateLoserTo()));
        }
        
        return predicates.toArray(new Predicate[predicates.size()]);
    }

    
}
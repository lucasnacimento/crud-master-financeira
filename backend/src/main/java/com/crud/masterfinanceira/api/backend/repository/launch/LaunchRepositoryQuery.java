package com.crud.masterfinanceira.api.backend.repository.launch;

import com.crud.masterfinanceira.api.backend.model.Launch;
import com.crud.masterfinanceira.api.backend.repository.filter.LaunchFilter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LaunchRepositoryQuery {
    
    public Page<Launch> filter(LaunchFilter launchFilter, Pageable pageable);
}

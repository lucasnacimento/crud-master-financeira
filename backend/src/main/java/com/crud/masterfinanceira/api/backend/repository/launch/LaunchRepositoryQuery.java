package com.crud.masterfinanceira.api.backend.repository.launch;

import java.util.List;

import com.crud.masterfinanceira.api.backend.model.Launch;
import com.crud.masterfinanceira.api.backend.repository.filter.LaunchFilter;

public interface LaunchRepositoryQuery {
    
    public List<Launch> filter(LaunchFilter launchFilter);
}

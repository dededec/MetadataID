package com.dededec.metadataid.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dededec.metadataid.model.PageAnalysis;
import com.dededec.metadataid.repository.PageAnalysisRepository;

@Service
public class PageAnalysisService {
    
    @Autowired
    private PageAnalysisRepository repository;

    public void insertAnalysis(PageAnalysis analysis) {
        repository.save(analysis);
    }
}

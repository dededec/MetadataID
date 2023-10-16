package com.dededec.metadataid.service;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.dededec.metadataid.model.PageAnalysis;
import com.dededec.metadataid.repository.PageAnalysisRepository;

@Service
public class PageAnalysisService {
    
    @Autowired
    private PageAnalysisRepository repository;

    public void insertAnalysis(PageAnalysis analysis) {
        analysis.setLastModified(Instant.now());
        repository.save(analysis);
    }

    public List<PageAnalysis> getLatestPageAnalyses(Integer limit) {
        return repository.findByOrderByLastModifiedDesc(PageRequest.of(0, limit));
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}

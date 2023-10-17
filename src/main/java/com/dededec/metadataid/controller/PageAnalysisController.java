package com.dededec.metadataid.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dededec.metadataid.model.PageAnalysis;
import com.dededec.metadataid.model.dto.PageAnalysisRequest;
import com.dededec.metadataid.service.PageAnalysisService;

@RestController
@RequestMapping("/analisis")
@CrossOrigin
public class PageAnalysisController {

    @Autowired
    private PageAnalysisService service;

    @PostMapping()
    public ResponseEntity<PageAnalysis> analyzeUrl(@RequestBody PageAnalysisRequest request) {
        PageAnalysis analysis = new PageAnalysis(request);
        service.insertAnalysis(analysis);
        return ResponseEntity.ok().body(analysis);
    } 

    @GetMapping()
    public ResponseEntity<List<PageAnalysis>> getLatestPageAnalyses(@RequestParam int limit) {
        List<PageAnalysis> result = service.getLatestPageAnalyses(limit);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PageAnalysis> deleteById(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.ok().body(null);
    }
    
}

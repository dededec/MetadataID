package com.dededec.metadataid.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dededec.metadataid.model.PageAnalysis;
import com.dededec.metadataid.service.PageAnalysisService;

@RestController
@RequestMapping("/analisis")
public class PageAnalysisController {

    @Autowired
    private PageAnalysisService service;

    @PostMapping()
    public ResponseEntity<PageAnalysis> analyzeUrl(@RequestBody String url) {
        PageAnalysis analysis = new PageAnalysis(url);
        service.insertAnalysis(analysis);
        return ResponseEntity.ok().body(analysis);
    } 
    
}

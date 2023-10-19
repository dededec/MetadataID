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

import com.dededec.metadataid.model.dto.AnalysisHistoryResponse;
import com.dededec.metadataid.model.dto.PageAnalysisRequest;
import com.dededec.metadataid.model.dto.PageAnalysisResponse;
import com.dededec.metadataid.model.entity.PageAnalysis;
import com.dededec.metadataid.service.PageAnalysisService;

@RestController
@RequestMapping("/analisis")
@CrossOrigin
public class PageAnalysisController {

    @Autowired
    private PageAnalysisService service;

    @PostMapping()
    public ResponseEntity<PageAnalysisResponse> analyzeUrl(@RequestBody PageAnalysisRequest request) {
        PageAnalysis analysis = service.fetchAnalysis(request.url);
        PageAnalysisResponse response = new PageAnalysisResponse(analysis);
        return ResponseEntity.ok().body(response);
    } 

    @GetMapping()
    public ResponseEntity<List<AnalysisHistoryResponse>> getLatestPageAnalyses(@RequestParam int limit) {
        List<AnalysisHistoryResponse> result = service.getAnalysisHistory(limit);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PageAnalysis> deleteById(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.ok().body(null);
    }
    
}

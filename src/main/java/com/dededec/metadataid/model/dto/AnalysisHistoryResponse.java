package com.dededec.metadataid.model.dto;

import java.time.Instant;

import com.dededec.metadataid.model.entity.PageAnalysis;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AnalysisHistoryResponse {
    private Integer id;
    private String url;
    private Instant lastModified;

    public AnalysisHistoryResponse(PageAnalysis analysis) {
        this.id = analysis.getId();
        this.url = analysis.getUrl();
        this.lastModified = analysis.getLastModified();
    }
}

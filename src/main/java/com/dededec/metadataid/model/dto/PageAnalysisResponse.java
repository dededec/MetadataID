package com.dededec.metadataid.model.dto;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dededec.metadataid.model.entity.PageAnalysis;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PageAnalysisResponse {

    private Integer id;
    private String url;
    private String title;
    private String description;
    private List<String> keywords;
    private Map<String, Integer> headings;
    private Boolean html5;
    private Integer images;
    private Instant lastModified;

    public PageAnalysisResponse(PageAnalysis analysis) {
        this.id = analysis.getId();
        this.url = analysis.getUrl();
        this.title = analysis.getTitle();
        this.description = analysis.getDescription();
        this.keywords = analysis.getKeywords();
        this.headings = new HashMap<>();
        if (!analysis.getHeadings().isBlank()) {
            Arrays.asList(analysis.getHeadings().split(","))
                    .stream().forEach(head -> headings.put(head.split(":")[0], Integer.parseInt(head.split(":")[1])));
        }
        this.html5 = analysis.getHtml5();
        this.images = analysis.getImages();
        this.lastModified = analysis.getLastModified();
    }

}

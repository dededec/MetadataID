package com.dededec.metadataid.model;

import java.time.Instant;

import org.springframework.data.annotation.LastModifiedDate;

import com.dededec.metadataid.model.dto.PageAnalysisRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class PageAnalysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;

    @Column
    private String url;

    @LastModifiedDate
    private Instant lastModified;

    public PageAnalysis(String url) {
        this.url = url;
    }

    public PageAnalysis(PageAnalysisRequest dto) {
        this.url = dto.url;
    }
}

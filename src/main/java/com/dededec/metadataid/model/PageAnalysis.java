package com.dededec.metadataid.model;

import java.time.Instant;

import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class PageAnalysis {
    @Id
    private Integer id;

    @Column
    private String url;

    @LastModifiedDate
    private Instant lastModified;
}

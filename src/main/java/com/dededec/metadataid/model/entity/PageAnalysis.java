package com.dededec.metadataid.model.entity;

import java.time.Instant;
import java.util.List;

import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageAnalysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String url;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private List<String> keywords;

    @Column
    private String headings;

    @Column
    private Boolean html5;

    @Column
    private Integer images;

    @LastModifiedDate
    private Instant lastModified;
}

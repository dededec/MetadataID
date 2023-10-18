package com.dededec.metadataid.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dededec.metadataid.model.entity.PageAnalysis;

public interface PageAnalysisRepository extends JpaRepository<PageAnalysis, Integer>{
    List<PageAnalysis> findByOrderByLastModifiedDesc(Pageable pageable);
    Optional<PageAnalysis> findByUrl(String url);
}

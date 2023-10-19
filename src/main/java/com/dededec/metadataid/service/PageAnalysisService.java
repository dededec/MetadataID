package com.dededec.metadataid.service;

import java.time.Instant;
import java.util.List;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.dededec.metadataid.model.dto.AnalysisHistoryResponse;
import com.dededec.metadataid.model.dto.PageAnalysisResponse;
import com.dededec.metadataid.model.entity.PageAnalysis;
import com.dededec.metadataid.repository.PageAnalysisRepository;

@Service
public class PageAnalysisService {

    @Autowired
    private PageAnalysisRepository repository;

    public PageAnalysis fetchAnalysis(String url) {
        url = url.trim();
        if(!url.startsWith("https://") && !url.startsWith("http://")) {
            url = "https://" + url;
        }
        var optCurrentAnalysis = repository.findByUrl(url);
        if (optCurrentAnalysis.isPresent()) {
            PageAnalysis currentAnalysis = optCurrentAnalysis.get();
            currentAnalysis.setLastModified(Instant.now());
            repository.save(currentAnalysis);
            return currentAnalysis;
        } else {
            PageAnalysis analysis = analyse(url);
            repository.save(analysis);
            return analysis;
        }
    }

    public List<AnalysisHistoryResponse> getAnalysisHistory(Integer limit) {
        return repository.findByOrderByLastModifiedDesc(PageRequest.of(0, limit)).stream()
                .map(analysis -> new AnalysisHistoryResponse(analysis)).toList();
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public PageAnalysisResponse findAnalysisById(Integer id) {
        var result = repository.findById(id);
        PageAnalysisResponse response = result.isPresent() ? new PageAnalysisResponse(result.get()) : null;
        return response;
    }

    private PageAnalysis analyse(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            return PageAnalysis.builder()
                    .url(url)
                    .title(PageAnalysisHelper.getTitle(doc))
                    .description(PageAnalysisHelper.getDescription(doc))
                    .keywords(PageAnalysisHelper.getKeywords(doc))
                    .headings(PageAnalysisHelper.getHeadings(doc))
                    .html5(PageAnalysisHelper.hasHTML5(doc))
                    .images(PageAnalysisHelper.getImagesNumber(doc))
                    .lastModified(Instant.now())
                    .build();
        } catch (Exception e) {
            Logger.getAnonymousLogger().severe(e.toString());
        }

        return null;
    }
}

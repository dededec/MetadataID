package com.dededec.metadataid;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.dededec.metadataid.model.dto.AnalysisHistoryResponse;
import com.dededec.metadataid.model.dto.PageAnalysisResponse;
import com.dededec.metadataid.model.entity.PageAnalysis;
import com.dededec.metadataid.repository.PageAnalysisRepository;
import com.dededec.metadataid.service.PageAnalysisService;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
class MetadataidApplicationTests {

	@Mock
	private PageAnalysisRepository repository;

	@InjectMocks
	private PageAnalysisService service;

	@Test
	public void givenURL_whenFetchAnalysis_thenReturnNotNull() throws Exception {

		// Given
		String url = "https://google.com";

		// When
		PageAnalysis analysis = service.fetchAnalysis(url);

		// Then
		assertNotNull(analysis);
	}

	@Test
	public void givenMalformedURL_whenFetchAnalysis_thenReturnNull() throws Exception {

		// Given
		String url = "wrong url";

		// When
		PageAnalysis analysis = service.fetchAnalysis(url);

		// Then
		assertNull(analysis);
	}

	@Test
	public void givenURLMissingHTTP_whenFetchAnalysis_thenReturnNotNull() throws Exception {

		// Given
		String url = "google.com";

		// When
		PageAnalysis analysis = service.fetchAnalysis(url);

		// Then
		assertNotNull(analysis);
	}

	@Test
	public void givenExistingURL_whenFetchAnalysis_thenReturnSameId() throws Exception {

		// Given
		String url = "google.com";
		PageAnalysis analysis = service.fetchAnalysis(url);

		// When
		PageAnalysis newAnalysis = service.fetchAnalysis(url);

		// Then
		assertEquals(analysis.getId(), newAnalysis.getId());
	}

	@Test
	public void givenNegativeNumber_whenFetchAnalysisHistory_thenReturnEmptyList() throws Exception {

		// Given
		int limit = -3;

		// When
		List<AnalysisHistoryResponse> history = service.getAnalysisHistory(limit);

		// Then
		assertTrue(history != null && history.isEmpty());
	}
}

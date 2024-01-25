package com.rahul.hotel.communicator;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.rahul.hotel.exceptions.HttpRatingServiceNotFound;

@Service
public class RatingServiceCommunicator {
	private final RestTemplate restTemplate;
	
	@Autowired
	public RatingServiceCommunicator(RestTemplateBuilder restTemplateBuilder) {
		restTemplate = restTemplateBuilder.build();
	}
	
	public Long getUpdatedRating(String id) {
		String url = "http://localhost:8081/rating/id/";
		
		ResponseEntity<Long> response = restTemplate.getForEntity(url+id, Long.class);
		return response.getBody();
	}
	
	public void addRating(Map<String, Long> ratingMap) {
		String url = "http://localhost:8081/rating/add";
//		restTemplate.postForObject(url, ratingMap, Object.class);
		HttpEntity<Map<String, Long>> requestEntity = new HttpEntity<>(ratingMap);
		restTemplate.exchange(url, HttpMethod.POST, requestEntity, Object.class);
	}
	
	public void updateRating(Map<String, Long> ratingMap) {
		String url = "http://localhost:8081/rating/update";
//		restTemplate.postForObject(url, ratingMap, Object.class);
		HttpEntity<Map<String, Long>> requestEntity = new HttpEntity<>(ratingMap);
		restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Object.class);
	}
	
	public void deleteRating(String id) {
		String url = "http://localhost:8081/rating/id/";
//		restTemplate.postForObject(url, ratingMap, Object.class);
//		HttpEntity<String> requestEntity = new HttpEntity<>(id);
		
		try {
			restTemplate.exchange(url+id, HttpMethod.DELETE, null, Object.class);
		} catch(HttpClientErrorException e) {
			throw new HttpRatingServiceNotFound(HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value()));
		}
	}
}

package com.rahul.hotel.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class HttpRatingServiceNotFound extends HttpClientErrorException {

	/**
	 * The serialVersionUID is a unique identifier for a serialized class. 
	 * It is used by the Java Virtual Machine (JVM) to ensure that the same class 
	 * is loaded during de-serialization as was used during serialization.
	 */
	private static final long serialVersionUID = 1L;

	public HttpRatingServiceNotFound(HttpStatusCode statusCode) {
		super(statusCode);
	}

}

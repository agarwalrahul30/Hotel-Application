package com.rahul.hotel.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class HotelNotValidException extends RuntimeException {
	public HotelNotValidException(String message) {
		super(message);
	}
}

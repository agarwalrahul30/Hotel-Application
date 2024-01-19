package com.rahul.hotel.domain;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

@Component
public class Hotel {
	@Min(value = 1)
	@Max(value = 10)
	private String id;
	@Size(min=3)
	private String name;
	
	public Hotel() {
		
	}
	
	public Hotel(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return "Hotel [id=" + id + ", name=" + name + "]";
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

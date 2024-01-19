package com.rahul.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rahul.hotel.domain.Hotel;
import com.rahul.hotel.exceptions.HotelNotValidException;
import com.rahul.hotel.service.HotelService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/hotel")
public class HotelController {
	
	@Autowired
	private HotelService hotelService;
	
	@PostMapping("/create")
	public void createHotel(@Valid @RequestBody Hotel hotel, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			throw new HotelNotValidException("Invalid fields in Hotel. Hotel cannot be added.");
		} else {
			hotelService.addHotel(hotel);
		}
	}
	
	@GetMapping("/id/{id}")
	public Hotel getHotelById(@PathVariable("id") String id) {
		return hotelService.getHotelById(id);
	}
	
	@GetMapping("/getAll")
	public List<Hotel> getAllHotels() {
		return hotelService.getAllHotels();
	}
	
	@DeleteMapping("/remove/id/{id}")
	public void deleteById(@PathVariable("id") String id) {
		hotelService.deleteHotelById(id);
	}
	
	@PutMapping("/update")
	public void updateHotel(@RequestBody Hotel updatedHotel) {
		hotelService.updateHotel(updatedHotel);
	}
}

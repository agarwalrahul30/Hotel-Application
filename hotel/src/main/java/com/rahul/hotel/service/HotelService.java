package com.rahul.hotel.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.rahul.hotel.domain.Hotel;
import com.rahul.hotel.exceptions.HotelNotFoundException;

@Service
public class HotelService {
	
	private List<Hotel> hotelList = new ArrayList<>();
	private Map<String, Hotel> hotelMapById = new HashMap<String, Hotel>();
	
	public void addHotel(Hotel hotel) {
		hotelList.add(hotel);
		hotelMapById.put(hotel.getId(), hotel);
	}
	
	public Hotel getHotelById(String id) {
		if(ObjectUtils.isEmpty(hotelMapById.get(id))) {
			throw new HotelNotFoundException("Sorry bro ye wala hotel nai hai");
		}
		return hotelMapById.get(id);
	}
	
	public List<Hotel> getAllHotels() {
		return hotelList;
	}
	
	public void deleteHotelById(String id) {
		hotelList.remove(getHotelById(id));
		hotelMapById.remove(id);
	}
	
	public void updateHotel(Hotel updatedHotel) {
		//update in List
		hotelList.remove(getHotelById(updatedHotel.getId()));
		hotelList.add(updatedHotel);
		
		//update in Map
		hotelMapById.put(updatedHotel.getId(), updatedHotel);
	}
}

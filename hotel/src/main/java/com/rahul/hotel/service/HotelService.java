package com.rahul.hotel.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.rahul.hotel.communicator.RatingServiceCommunicator;
import com.rahul.hotel.domain.Hotel;
import com.rahul.hotel.exceptions.HotelNotFoundException;

@Service
public class HotelService {
	
	private List<Hotel> hotelList = new ArrayList<>();
	private Map<String, Hotel> hotelMapById = new HashMap<String, Hotel>();
	
	@Autowired
	RatingServiceCommunicator ratingServiceCommunicator;
	
	public void addHotel(Hotel hotel) {
		Map<String, Long> ratingMap = new HashMap<>();
		if(!hotelMapById.containsKey(hotel.getId())) {
			hotelList.add(hotel);
			hotelMapById.put(hotel.getId(), hotel);
			ratingMap.put(hotel.getId(), hotel.getRating());
			ratingServiceCommunicator.addRating(ratingMap);
		}
	}
	
	public Hotel getHotelById(String id) {
		if(ObjectUtils.isEmpty(hotelMapById.get(id))) {
			throw new HotelNotFoundException("Hotel with id:"+id+" does not exist.");
		}
		
		Hotel hotel = hotelMapById.get(id);
		Long updatedRating = ratingServiceCommunicator.getUpdatedRating(id);
		hotel.setRating(updatedRating);
		
		return hotel;
	}
	
	public List<Hotel> getAllHotels() {
		return hotelList;
	}
	
	public void deleteHotelById(String id) {
		hotelList.remove(getHotelById(id));
		hotelMapById.remove(id);
		
		ratingServiceCommunicator.deleteRating(id);
	}
	
	public void updateHotel(Hotel updatedHotel) {
		//update in List
		hotelList.remove(getHotelById(updatedHotel.getId()));
		hotelList.add(updatedHotel);
		
		//update in Map
		hotelMapById.put(updatedHotel.getId(), updatedHotel);
		
		Map<String, Long> ratingMap = new HashMap<>();
		ratingMap.put(updatedHotel.getId(), updatedHotel.getRating());
		ratingServiceCommunicator.updateRating(ratingMap);
	}
}

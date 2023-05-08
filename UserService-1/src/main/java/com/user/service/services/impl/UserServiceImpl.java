package com.user.service.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.service.entities.Hotel;
import com.user.service.entities.Rating;
import com.user.service.entities.User;
import com.user.service.exceptions.ResourceNotFoundException;
import com.user.service.repositories.UserRepository;
import com.user.service.services.UserService;


@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	

	@Override
	public User saveUser(User user) {
		String userId = UUID.randomUUID().toString();
		user.setUserId(userId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUser(String userId) {
		User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with given id is not found on server !!! "+userId));
		
		Rating[] rating = restTemplate.getForEntity("http://localhost:8083/ratings/users/"+userId, Rating[].class).getBody();
		
		List<Rating> ratings = Arrays.stream(rating).map(ratin->
		{
			ResponseEntity<Hotel> hotel = restTemplate.getForEntity("http://localhost:8082/hotels/"+ratin.getHotelId(), Hotel.class);
			
			ratin.setHotel(hotel.getBody());
			return ratin;
		}
				).collect(Collectors.toList());
		user.setRatings(ratings);
		return user;
	} 

	@Override
	public void deleteUser(String userId) {
		userRepository.deleteById(userId);
	}

	@Override
	public User updateUser(String userId) {
		return null;
	}
	

}

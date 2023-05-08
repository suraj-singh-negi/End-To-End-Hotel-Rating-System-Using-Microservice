package com.rating.service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rating.service.entities.Rating;
import com.rating.service.services.RatingService;

@RestController
@RequestMapping("/ratings")
public class RatingController {
	
	@Autowired
	private RatingService ratingService;

	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping
	public ResponseEntity<Rating> create(@RequestBody Rating rating){
		return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.create(rating));
	}
	
	@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
	@GetMapping
	public ResponseEntity<List<Rating>> getRatings(){
		return ResponseEntity.ok(ratingService.getRatings());
	}
	
	@PreAuthorize("hasAuthority('SCOPE_internal')")
	@GetMapping("/users/{userId}")
	public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String userId){
		return ResponseEntity.ok(ratingService.getRatingsByUserId(userId));
	}
	
	@PreAuthorize("hasAuthority('SCOPE_internal')")
	@GetMapping("/hotels/{hotelId}")
	public ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable String hotelId){
		return ResponseEntity.ok(ratingService.getRatingsByHotelId(hotelId));
	}
	
}

package com.user.service.externalservice;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="RATING-SERVICE")
public interface RatingService {
	
	

}

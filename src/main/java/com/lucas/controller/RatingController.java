package com.lucas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.exception.ProductException;
import com.lucas.exception.UserException;
import com.lucas.model.Rating;
import com.lucas.model.User;
import com.lucas.request.RatingRequest;
import com.lucas.service.RatingService;
import com.lucas.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


@RestController
@RequestMapping("/api/ratings")
public class RatingController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private RatingService ratingService;
	
	@PostMapping("/create")
	public ResponseEntity<Rating>createRating(@RequestBody RatingRequest req, @RequestHeader("Authorization") String jwt) throws UserException, ProductException{
		User user = userService.findUserProfileByJwt(jwt);
		Rating rating = ratingService.createRating(req, user);
		
		return new ResponseEntity<Rating>(rating,HttpStatus.CREATED);
	}
	
	@GetMapping("/product/{productId}")
	public ResponseEntity<List<Rating>>getProductsRating(@PathVariable Long productId, @RequestHeader("Authorization") String jwt) throws UserException, ProductException{
		User user=userService.findUserProfileByJwt(jwt);
		List<Rating> ratings=ratingService.getProductRating(productId);
		
		return new ResponseEntity<>(ratings,HttpStatus.CREATED);
	}
	
	
	
	
	
	
	
	
	
	
}

package com.lucas.service;

import java.util.List;

import com.lucas.exception.ProductException;
import com.lucas.model.Rating;
import com.lucas.model.User;
import com.lucas.request.RatingRequest;

public interface RatingService {
	public Rating createRating(RatingRequest req, User user) throws ProductException;
	public List<Rating> getProductRating(Long productId);
	
}

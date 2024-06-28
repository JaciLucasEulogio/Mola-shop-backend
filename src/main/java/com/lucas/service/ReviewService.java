package com.lucas.service;

import java.util.List;

import com.lucas.exception.ProductException;
import com.lucas.model.Review;
import com.lucas.model.User;
import com.lucas.request.ReviewRequest;

public interface ReviewService {
	public Review createReview(ReviewRequest req, User user)throws ProductException;
	public List<Review> getAllReview(Long productId);
}

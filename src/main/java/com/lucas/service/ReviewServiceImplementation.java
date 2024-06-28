package com.lucas.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lucas.exception.ProductException;
import com.lucas.model.Product;
import com.lucas.model.Review;
import com.lucas.model.User;
import com.lucas.repository.ProductRepository;
import com.lucas.repository.ReviewRepository;
import com.lucas.request.ReviewRequest;

@Service
public class ReviewServiceImplementation implements ReviewService{
	
	private ReviewRepository reviewRepository;
	private ProductService productService;
	private ProductRepository productRepository;
	
	
	public ReviewServiceImplementation(ReviewRepository reviewRepository, ProductService productService,
			ProductRepository productRepository) {
		super();
		this.reviewRepository = reviewRepository;
		this.productService = productService;
		//this.productRepository = productRepository;
	}

	@Override
	public Review createReview(ReviewRequest req, User user) throws ProductException {
		Product product= productService.findProductById(req.getProductId());
		
		Review review=new Review();
		review.setUser(user);
		review.setProduct(product);
		review.setReview(req.getReview());
		review.setCreatedAt(LocalDateTime.now());
		return reviewRepository.save(review);
	}

	@Override
	public List<Review> getAllReview(Long productId) {
		
		return reviewRepository.getAllProductsReview(productId);
	}

}

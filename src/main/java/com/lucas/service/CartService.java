package com.lucas.service;

import com.lucas.exception.ProductException;
import com.lucas.model.Cart;
import com.lucas.model.User;
import com.lucas.request.AddItemRequest;

public interface CartService {
	public Cart createCart(User user);
	public String addCartItem(Long userId,AddItemRequest req) throws ProductException;
	public Cart findUserCart(Long userId);
}

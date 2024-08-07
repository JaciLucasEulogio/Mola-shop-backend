package com.lucas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.exception.OrderException;
import com.lucas.exception.UserException;
import com.lucas.model.Address;
import com.lucas.model.Order;
import com.lucas.model.User;
import com.lucas.service.OrderService;
import com.lucas.service.UserService;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<Order>createOrder(@RequestBody Address shippingAddress, @RequestHeader("Authorization") String jwt) throws UserException {
		User user = userService.findUserProfileByJwt(jwt);
		Order order =orderService.createOrder(user, shippingAddress);
		System.out.println("order"+order);
		
		return new ResponseEntity<Order>(order,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/user")
	public ResponseEntity<List<Order>> userOrderHistory(@RequestHeader("Authorization") String jwt) throws UserException {
	    User user = userService.findUserProfileByJwt(jwt);
	    if (user == null) {
	        
	        throw new UserException("User not found");
	    }
	    System.out.println("User ID: " + user.getId()); 
	    List<Order> orders = orderService.userOrderHistory(user.getId());
	    System.out.println("Orders size: " + orders.size()); 

	    return new ResponseEntity<>(orders, HttpStatus.OK); 
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Order>findOrderById(@PathVariable("id")Long orderId, @RequestHeader("Authorization") String jwt) throws UserException, OrderException{
		User user=userService.findUserProfileByJwt(jwt);
		Order order = orderService.findOrderById(orderId);
		
		return new ResponseEntity<>(order,HttpStatus.ACCEPTED);
	}
	
	
}

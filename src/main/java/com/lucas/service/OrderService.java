package com.lucas.service;

import java.util.List;

import com.lucas.exception.OrderException;
import com.lucas.model.Address;
import com.lucas.model.Order;
import com.lucas.model.User;

public interface OrderService {
	public Order createOrder(User user, Address shippingAddress);
	public Order findOrderById(Long orderId)throws OrderException;
	public List<Order> userOrderHistory(Long userId);
	public Order placedOrder(Long orderId) throws OrderException;
	public Order confirmedOrder(Long orderId) throws OrderException;
	public Order shippedOrder(Long orderId) throws OrderException;
	public Order deliveredOrder(Long orderId) throws OrderException;
	public Order canceledOrder(Long orderId) throws OrderException;
	public List<Order>getAllOrders();
	public void deleteOrder(Long orderId) throws OrderException;
}

package com.lucas.service;

import com.lucas.exception.OrderItemException;
import com.lucas.exception.UserException;
import com.lucas.model.Order;
import com.lucas.model.OrderItem;
import com.lucas.model.Product;

public interface OrderItemService {
	public OrderItem createOrderItem(OrderItem orderItem);
	public OrderItem updateOrderItem(Long userId, Long id, OrderItem orderItem) throws OrderItemException, UserException;
	public OrderItem isOrderItemExist(Order order, Product product, String size, Long userId);
	public void removeOrderItem(Long userId,Long orderItemId) throws OrderItemException,UserException;
	public OrderItem findOrderItemById(Long orderItemId) throws OrderItemException;
}

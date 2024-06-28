package com.lucas.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucas.exception.OrderItemException;
import com.lucas.exception.UserException;
import com.lucas.model.Order;
import com.lucas.model.OrderItem;
import com.lucas.model.Product;
import com.lucas.model.User;
import com.lucas.repository.OrderItemRepository;
import com.lucas.repository.OrderRepository;

@Service
public class OrderItemServiceImplementation implements OrderItemService {
	
	@Autowired
    private OrderItemRepository orderItemRepository;
	
	@Autowired
    private UserService userService;
	
	@Autowired
    private OrderRepository orderRepository;

    public OrderItemServiceImplementation(OrderItemRepository orderItemRepository, UserService userService,
            OrderRepository orderRepository) {
        super();
        this.orderItemRepository = orderItemRepository;
        this.userService = userService;
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {
        /*orderItem.setQuantity(1);
        orderItem.setPrice(orderItem.getProduct().getPrice() * orderItem.getQuantity());
        orderItem.setDiscountedPrice(orderItem.getProduct().getDiscountedPrice() * orderItem.getQuantity());
        OrderItem createdOrderItem = orderItemRepository.save(orderItem);*/

        //return createdOrderItem;
    	return orderItemRepository.save(orderItem);
    }

    @Override
    public OrderItem updateOrderItem(Long userId, Long id, OrderItem orderItem)
            throws OrderItemException, UserException {
        OrderItem item = findOrderItemById(id);
        User user = userService.findUserById(item.getUserId());
        if (user.getId().equals(userId)) {
            item.setQuantity(orderItem.getQuantity());
            item.setPrice(item.getQuantity() * item.getProduct().getPrice());
            item.setDiscountedPrice(item.getProduct().getDiscountedPrice() * item.getQuantity());
        }
        return orderItemRepository.save(item);
    }

    @Override
    public OrderItem isOrderItemExist(Order order, Product product, String size, Long userId) {
        OrderItem orderItem = orderItemRepository.isOrderItemExist(order, product, size, userId);
        return orderItem;
    }

    @Override
    public void removeOrderItem(Long userId, Long orderItemId) throws OrderItemException, UserException {
        OrderItem orderItem = findOrderItemById(orderItemId);
        User user = userService.findUserById(orderItem.getUserId());
        User reqUser = userService.findUserById(userId);
        if (user.getId().equals(reqUser.getId())) {
            orderItemRepository.deleteById(orderItemId);
        } else {
            throw new UserException("you can't remove another user's item");
        }
    }

    @Override
    public OrderItem findOrderItemById(Long orderItemId) throws OrderItemException {
        Optional<OrderItem> opt = orderItemRepository.findById(orderItemId);
        if (opt.isPresent()) {
            return opt.get();
        }
        throw new OrderItemException("OrderItem not found with id:" + orderItemId);
    }

}

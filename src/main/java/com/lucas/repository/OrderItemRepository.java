package com.lucas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lucas.model.Order;
import com.lucas.model.OrderItem;
import com.lucas.model.Product;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
	
	@Query("SELECT ci FROM OrderItem ci WHERE ci.order = :order AND ci.product = :product AND ci.size = :size AND ci.userId = :userId")
	public OrderItem isOrderItemExist(@Param("order") Order order,@Param("product")Product product,@Param("size")String size, @Param("userId")Long userId);
	
}

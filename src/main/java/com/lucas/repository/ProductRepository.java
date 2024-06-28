package com.lucas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lucas.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
    @Query("SELECT p FROM Product p " +
            "WHERE (p.category.name = :category OR :category ='') " +
            "AND ((:minPrice IS NULL AND :maxPrice IS NULL) OR (p.price BETWEEN :minPrice AND :maxPrice)) " +
            "AND (:minDiscount IS NULL OR p.discountPercent >= :minDiscount) " +
            "ORDER BY " +
            "CASE WHEN :sort = 'price_low' THEN p.price END ASC, " +
            "CASE WHEN :sort = 'price_high' THEN p.price END DESC")
    public List<Product> filterProducts(@Param("category") String category,
                                 @Param("minPrice") Integer minPrice,
                                 @Param("maxPrice") Integer maxPrice,
                                 @Param("minDiscount") Integer minDiscount,
                                 @Param("sort") String sort);
    
    //Search
    @Query("SELECT p FROM Product p " +
            "WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :query, '%')) " +
            "OR LOWER(p.description) LIKE LOWER(CONCAT('%', :query, '%')) " +
            "OR LOWER(p.brand) LIKE LOWER(CONCAT('%', :query, '%'))")
    public List<Product> searchProducts(@Param("query") String query);
    
    @Query("SELECT p FROM Product p " +
            "WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :query, '%')) " +
            "OR LOWER(p.description) LIKE LOWER(CONCAT('%', :query, '%')) " +
            "OR LOWER(p.brand) LIKE LOWER(CONCAT('%', :query, '%'))")
    public List<Product> findByTitleContainingIgnoreCase(String query);
}


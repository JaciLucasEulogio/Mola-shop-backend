package com.lucas.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.lucas.exception.ProductException;
import com.lucas.model.Product;
import com.lucas.request.CreateProductRequest;

public interface ProductService {
	public Product createProduct(CreateProductRequest req);
	public String deteleProduct(Long productId) throws ProductException;
	public Product updateProduct(Long productId, Product req) throws ProductException;
	public Product findProductById(Long id) throws ProductException;
	public List<Product> findProductByCategory(String category);
	public Page<Product>getAllProduct(String category,List<String>colors,List<String>sizes,Integer minPrice, Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize);
	//findAllProducts
    public List<Product> findAllProducts();
    //searchProduct
    public List<Product> searchProduct(String query);
}

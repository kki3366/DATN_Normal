package com.DATN.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DATN.Entity.Product;

public interface ProductService {

	Product saveProductsService(Product products);
	
	List<Product> findAllProductService();
	
	List<Product> findProductsByNameService(String productName);
	
	void deleteProductsById(int id);
	
	Product findById(Integer id);
	
	Optional<Product> findByIdProducts(int id);
}

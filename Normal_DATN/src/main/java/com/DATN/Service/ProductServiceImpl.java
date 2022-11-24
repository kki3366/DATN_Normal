package com.DATN.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DATN.Entity.Product;
import com.DATN.Repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductRepository productRepo;

	@Override
	public Product saveProductsService(Product products) {
		return productRepo.save(products);
	}

	@Override
	public List<Product> findAllProductService() {
		return productRepo.findAll();
	}

	@Override
	public List<Product> findProductsByNameService(String productName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteProductsById(int id) {
		 productRepo.deleteById(id);
	}

	@Override
	public Product findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Product> findByIdProducts(int id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

}

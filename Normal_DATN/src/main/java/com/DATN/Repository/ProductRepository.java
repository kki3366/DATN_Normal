package com.DATN.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.DATN.Entity.Product;



public interface ProductRepository extends JpaRepository<Product, Integer>{
	@Query("SELECT o FROM Product o WHERE o.category.id = ?1 and o.subcategory.id = ?2")
	Page<Product> findByShop(Integer category, Integer subcategory, Pageable pageable);
	
	@Query(value = "select count(Productid) from Products where Name LIKE :name", nativeQuery = true)
	int checkProductisExit(@Param("name") String nameProduct);
}

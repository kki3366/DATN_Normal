package com.DATN.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.DATN.Entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

	
	@Query(value = "select count(Categoryid) from category where name LIKE :name", nativeQuery = true)
	int IsExitCategory(@Param("name") String name);
}

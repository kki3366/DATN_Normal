package com.DATN.Service;

import java.util.List;
import java.util.Optional;

import com.DATN.Entity.Category;


public interface CategoryService {

	Category saveCategoryService(Category category);
	
	List<Category> findAllCategoryService();
	
    List<Category> findCategoryByNameService(String categoryName);
	
	void deleteCategoryById(int id);
	
	Optional<Category> findByIdCategory(int id);
	
	int checkCategoryName(String name);
}

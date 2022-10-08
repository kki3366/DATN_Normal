package com.DATN.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.DATN.Entity.Category;
@Service  
public class CategoryServiceImpl implements CategoryService {

	@Override
	public Category saveCategoryService(Category category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> findAllCategoryService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> findCategoryByNameService(String categoryName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCategoryById(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<Category> findByIdCategory(int id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}


	

}

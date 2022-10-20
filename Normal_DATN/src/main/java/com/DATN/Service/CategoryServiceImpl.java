package com.DATN.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DATN.Entity.Category;
import com.DATN.Repository.CategoryRepository;
@Service  
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	CategoryRepository cateRepo;

	@Override
	public Category saveCategoryService(Category category) {
		return cateRepo.save(category);
	}

	@Override
	public List<Category> findAllCategoryService() {
		List<Category> list = cateRepo.findAll();
		return list;
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

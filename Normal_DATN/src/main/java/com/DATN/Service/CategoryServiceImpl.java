package com.DATN.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.DATN.Entity.Category;
import com.DATN.Repository.CategoryRepository;
@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	CategoryRepository cateRepo;

	@Override
	@Transactional(rollbackFor = { SQLException.class })
	public Category saveCategoryService(Category category) {
		return cateRepo.save(category);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Category> findAllCategoryService() {
		List<Category> list = cateRepo.findAll();
		return list;
	}


	@Override
	public void deleteCategoryById(int id) {
		cateRepo.deleteById(id);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Optional<Category> findByIdCategory(int id) {
		return cateRepo.findById(id);
	}

	@Override
	public int checkCategoryName(String name) {
		return cateRepo.IsExitCategory(name);
	}

	@Override
	public int checkProductExitInCategory(int id) {
		return cateRepo.checkProductExitInCategory(id);
	}


	

}

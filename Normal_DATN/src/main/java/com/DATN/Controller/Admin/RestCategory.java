package com.DATN.Controller.Admin;

import java.util.stream.Collectors;

import javax.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.DATN.Entity.Category;
import com.DATN.Service.CategoryService;

@RestController
@RequestMapping("api")
@Validated
public class RestCategory {

	@Autowired
	CategoryService categoryService;
	
	@GetMapping(value = "/categories")
	public ResponseEntity<List<Category>> findAllCategory(){
		return new ResponseEntity<List<Category>>(categoryService.findAllCategoryService().stream().collect(Collectors.toList()),HttpStatus.OK);
	}
	
	@PostMapping(value = "/categories", consumes = "application/json")
	public ResponseEntity<Category> saveCategory(@RequestBody @Valid Category category){
//		if(categoryService.checkCategoryName(category.getName())> 0) {
//			return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
//		}else {
//			return new ResponseEntity<Category>(categoryService.saveCategoryService(category),HttpStatus.CREATED);
//		}
		return new ResponseEntity<Category>(categoryService.saveCategoryService(category),HttpStatus.CREATED);
	}
	
}

package com.DATN.Controller.Admin;

import java.util.stream.Collectors;

import javax.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.DATN.Entity.Category;
import com.DATN.Repository.CategoryRepository;
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
		System.err.println(category.getNameCategory());
		if(categoryService.checkCategoryName(category.getNameCategory())> 0) {
			return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
		}else {
			return new ResponseEntity<Category>(categoryService.saveCategoryService(category),HttpStatus.CREATED);
		}
	}
	
	
//	@PutMapping(value = "/categories/{id}")
//	public ResponseEntity<Category> updateCategory(@PathVariable("id") int id, @RequestBody Category category){
//		Optional<Category> categoryOption = categoryService.findByIdCategory(id);
//			return (ResponseEntity<Category>) categoryOption.map(c -> {
//				category.setId(c.getId());
//				return new ResponseEntity<>(categoryService.saveCategoryService(category),HttpStatus.OK);
//			}).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//	}
	@PutMapping(value = "/categories")
	public ResponseEntity<Category> updateCategory1(@RequestBody Category category){
		if(categoryService.checkCategoryName(category.getNameCategory()) > 0) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}else {
			Optional<Category> categoryOption = categoryService.findByIdCategory(category.getId());
			System.err.println(category.getId());
			return (ResponseEntity<Category>) categoryOption.map(c -> {
				category.setId(c.getId());
				return new ResponseEntity<>(categoryService.saveCategoryService(category),HttpStatus.OK);
			}).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
		}
	}

	
	@DeleteMapping("/categories/{idCategory}")
	public ResponseEntity<HttpStatus> deleteCategoryById(@PathVariable("idCategory") int id){
		if(categoryService.checkProductExitInCategory(id) > 0) {
			return new ResponseEntity<HttpStatus>(HttpStatus.CONFLICT);
		}else {
			categoryService.deleteCategoryById(id);
			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
		}
	}
	
}

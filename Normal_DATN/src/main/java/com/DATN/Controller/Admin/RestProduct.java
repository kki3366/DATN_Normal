package com.DATN.Controller.Admin;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.DATN.Entity.Product;
import com.DATN.Service.ProductService;
import com.DATN.Unit.FileUploadUtil;

@RestController
@RequestMapping("api")
@Validated
public class RestProduct {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	ServletContext app;
	
	
	@GetMapping(value = "/products", produces = "application/json")
	public ResponseEntity<List<Product>> findAllCategory(){
		return new ResponseEntity<List<Product>>(productService.findAllProductService().stream().collect(Collectors.toList()),HttpStatus.OK);
	}
	
	
	@PostMapping(value = "/products", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<Product> saveProduct(
			@RequestPart(value = "fileProduct", required = false) MultipartFile file, @RequestPart @Valid Product products,
			BindingResult result) throws IllegalStateException, IOException{
		if(result.hasErrors()) {
			return null;
		}else {
			FileUploadUtil fileUtil = new FileUploadUtil();
			fileUtil.saveFile(file, app);
			products.setImgage(fileUtil.getGetFileNameForEntity());
			products.setAvailable(true);
			return new ResponseEntity<Product>(productService.saveProductsService(products),HttpStatus.CREATED);
		}

	}
	
	
//	@PutMapping(value = "/products/{id}", consumes = "application/json")
//	public ResponseEntity<Product> updateProducts(@PathVariable("id") int id,@RequestBody Product products){
//		Optional<Product> productsOption = productService.findByIdProducts(id);
//		System.err.println(productsOption.get().getName());
//		return (ResponseEntity<Product>) productsOption.map(p -> {
//			products.setId(p.getId());
//			products.setName(p.getName());
//			products.setPrice(p.getPrice());
//			products.setImgage(p.getImage());
//			products.setDate(p.getDate());
//			products.setAvailable(p.isAvailable());
//			products.setCategory(p.getCategory());
//			products.setQuantity(p.getQuantity());
//			products.setDescription(p.getDescription());
//			products.setDiscount(p.getDiscount());
//			products.setViewCount(p.getViewCount());
//			products.setSpecial(p.isSpecial());
//			return new ResponseEntity<>(productService.saveProductsService(products),HttpStatus.OK);
//		}).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//	}

	@PutMapping(value = "/products", consumes = "application/json")
	public ResponseEntity<Product> updateProducts(@RequestBody Product products){
		Optional<Product> productsOption = productService.findByIdProducts(products.getId());
		System.err.println(productsOption.get().getName());
		return (ResponseEntity<Product>) productsOption.map(p -> {
			products.setId(p.getId());
			return new ResponseEntity<>(productService.saveProductsService(products),HttpStatus.OK);
		}).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@DeleteMapping("/products/{idProducts}/{image}")
	public ResponseEntity<HttpStatus> deleteProductsById(@PathVariable("idProducts") String id, @PathVariable("image") String image) throws IOException{
		FileUploadUtil fileUtil = new FileUploadUtil();
		fileUtil.deleteFileByName(image, app);
		productService.deleteProductsById(Integer.parseInt(id));
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}
}

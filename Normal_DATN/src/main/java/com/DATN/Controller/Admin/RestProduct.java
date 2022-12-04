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

import com.DATN.Entity.Category;
import com.DATN.Entity.Product;
import com.DATN.Service.CartService;
import com.DATN.Service.ProductService;
import com.DATN.Unit.FileUploadUtil;

@RestController
@RequestMapping("api")
@Validated
public class RestProduct {

	@Autowired
	ProductService productService;
	
	@Autowired
	CartService cartService;

	@Autowired
	ServletContext app;

	@GetMapping(value = "/products", produces = "application/json")
	public ResponseEntity<List<Product>> findAllCategory() {
		return new ResponseEntity<List<Product>>(
				productService.findAllProductService().stream().collect(Collectors.toList()), HttpStatus.OK);
	}

	@PostMapping(value = "/products", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE,
			MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<Product> saveProduct(@RequestPart(value = "fileProduct", required = false) MultipartFile file,
			@RequestPart @Valid Product products, BindingResult result) throws IllegalStateException, IOException {
		if (result.hasErrors()) {
			return null;
		} else {
				if(products.getCategory().getId() == null) {
					System.err.println("dang null");
					return new ResponseEntity<>(HttpStatus.CONFLICT); 
				}else {
					FileUploadUtil fileUtil = new FileUploadUtil();
					fileUtil.saveFile(file, app);
					products.setImgage(fileUtil.getGetFileNameForEntity());
					products.setAvailable(true);
					return new ResponseEntity<Product>(productService.saveProductsService(products), HttpStatus.CREATED);
				}
		}

	}

	
	@PutMapping(value = "/products", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE,MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	public ResponseEntity<Product> updateProducts(@RequestPart(value = "fileUpdate", required = false) MultipartFile file,
			@RequestPart @Valid Product productsUpdate, BindingResult result) throws IllegalStateException, IOException{
		if(result.hasErrors()) {
			return null;
		}
		if(file == null) {
			Optional<Product> productsOption = productService.findByIdProducts(productsUpdate.getId());
			return (ResponseEntity<Product>) productsOption.map(p -> {
				productsUpdate.setId(p.getId());
				productsUpdate.setAvailable(true);
				return new ResponseEntity<>(productService.saveProductsService(productsUpdate), HttpStatus.OK);
			}).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//			System.err.println(productsUpdate.getId());
		}else {
			FileUploadUtil fileUtil = new FileUploadUtil();
			fileUtil.deleteFileByName(productsUpdate.getImage(), app);
			fileUtil.saveFile(file, app);
			Optional<Product> productsOption = productService.findByIdProducts(productsUpdate.getId());
			return (ResponseEntity<Product>) productsOption.map(p -> {
				productsUpdate.setId(p.getId());
				productsUpdate.setImgage(fileUtil.getGetFileNameForEntity());
				productsUpdate.setAvailable(true);
				return new ResponseEntity<>(productService.saveProductsService(productsUpdate), HttpStatus.OK);
			}).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));	
		}
	}

	@DeleteMapping("/products/{idProducts}/{getImg}")
	public ResponseEntity<HttpStatus> deleteProductsById(@PathVariable("idProducts") int id,
			@PathVariable("getImg") String image) throws IOException {
		//System.err.println(image);
		FileUploadUtil fileUtil = new FileUploadUtil();
		fileUtil.deleteFileByName(image, app);
		cartService.deleleCartByProductId(id);
		productService.deleteProductsById(id);
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}
}

package com.wipro.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.exception.CouponNotFoundException;
import com.wipro.exception.ProductNotFoundException;
import com.wipro.model.Product;
import com.wipro.model.PurchaseDetails;
import com.wipro.service.ProductService;

@RefreshScope
@RestController
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	private ProductService service;
	
	@PostMapping("/save")
	public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
		Product prod = service.addProduct(product);
		return new ResponseEntity<Product>(prod, HttpStatus.CREATED);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Product> showProductById(@PathVariable int id) throws ProductNotFoundException{
		Product product = service.getProductById(id);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	@PostMapping("/purchase")
	public ResponseEntity<PurchaseDetails> applyCoupon(@RequestParam int productId, @RequestParam(required = false) String code) throws ProductNotFoundException, CouponNotFoundException {
		PurchaseDetails purchaseDetails = service.applyCouponCode(productId, code);
		return new ResponseEntity<PurchaseDetails>(purchaseDetails, HttpStatus.OK);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Product>> showAllProducts() throws ProductNotFoundException{
		List<Product> products = service.getAllProducts();
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable int id) throws ProductNotFoundException {
		Product prod = service.updateProduct(product, id);
		return new ResponseEntity<Product>(prod, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteProduct(@PathVariable int id) throws ProductNotFoundException {
		Map<String, Boolean> response = service.deleteProduct(id);
		return new ResponseEntity<Map<String,Boolean>>(response, HttpStatus.OK);
	}
}

package com.wipro.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.exception.CouponNotFoundException;
import com.wipro.exception.InventoryNotFoundException;
import com.wipro.exception.ProductNotFoundException;
import com.wipro.model.Coupon;
import com.wipro.model.Inventory;
import com.wipro.model.Product;
import com.wipro.model.PurchaseDetails;
import com.wipro.repo.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	@Autowired
	private ProductProxy proxy;

	@Autowired
	private InventoryProxy inventoryProxy;

	public List<Product> getAllProducts() throws ProductNotFoundException {
		List<Product> products = repository.findAll();

		if (products.isEmpty()) {
			throw new ProductNotFoundException("There are no Products in the database");
		}

		return products;
	}

	public Product getProductById(int id) throws ProductNotFoundException {
		Optional<Product> product = repository.findById(id);
		if (product.isEmpty()) {
			throw new ProductNotFoundException("No Product Found with this Id: " + id);
		}
		Product prod = product.get();
		return prod;
	}

	public PurchaseDetails applyCouponCode(int productId, String code)
			throws ProductNotFoundException, CouponNotFoundException {
		Optional<Coupon> coup = proxy.getByCouponCode(code);
		if (coup.isEmpty()) {
			throw new CouponNotFoundException("Coupon Not Found");
		}
		Coupon coupon = coup.get();

		Optional<Product> product = repository.findById(productId);
		if (product.isEmpty()) {
			throw new ProductNotFoundException("Product Not Found");
		}
		Product prod = product.get();

		Optional<Inventory> invOpt = inventoryProxy.getByProductId(productId);
		if (invOpt.isEmpty()) {
			throw new InventoryNotFoundException("No Inventory Found");
		}

		Inventory inventory = invOpt.get();
		int totalQuantity = inventory.getQuantity();
		
		if (totalQuantity < 1) {
			throw new ProductNotFoundException("Out of Stock!");
		}

		inventory.setQuantity(totalQuantity - 1);
		Inventory updateInventory = inventoryProxy.updateInventory(productId, inventory);

		if (null == updateInventory) {
			System.err.println("Something went wrong!");
		}

		if (!coupon.isValid()) {
			throw new CouponNotFoundException("Coupon has been expired!");
		}

		double discountedPrice = 0;
		double price = prod.getProductPrice();
		int percentage = coupon.getDiscountPercentage();

		discountedPrice = price - calculateDiscount(price, percentage);
		PurchaseDetails purchaseDetails = new PurchaseDetails(prod.getProductName(), price, discountedPrice);
		return purchaseDetails;
	}

	public Product addProduct(Product product) {
		repository.save(product);
		return product;
	}

	public Product updateProduct(Product product, int id) throws ProductNotFoundException {

		Optional<Product> prod = repository.findById(id);

		if (prod.isEmpty()) {
			throw new ProductNotFoundException("No Product Found with this Id: " + id);
		}

		Product pro = prod.get();

		pro.setProductId(id);
		pro.setProductName(product.getProductName());
		pro.setProductPrice(product.getProductPrice());

		repository.save(pro);

		return product;
	}

	public Map<String, Boolean> deleteProduct(int id) throws ProductNotFoundException {
		Map<String, Boolean> response = new HashMap<>();
		Optional<Product> prod = repository.findById(id);

		if (prod.isEmpty()) {
			throw new ProductNotFoundException("No Product Found with this Id: " + id);
		}
		Product product = prod.get();
		repository.delete(product);
		response.put("Product Deleted", true);
		return response;
	}

	private double calculateDiscount(double originalPrice, int percentage) {
		return originalPrice * percentage / 100;
	}
}

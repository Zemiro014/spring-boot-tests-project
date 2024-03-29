package com.devsuperioi.dscatalog.tests;

import java.time.Instant;

import com.devsuperioi.dscatalog.dto.ProductDTO;
import com.devsuperioi.dscatalog.entities.Category;
import com.devsuperioi.dscatalog.entities.Product;

public class Factory {

	public static Product createProduct() {
		Product product = new Product(1L, "phone", "Good phone", 800.0, "https://img.com/img.png", Instant.parse("2020-10-20T03:00:00Z"));
		product.getCategories().add(new Category(2L, "Electronics"));
		return product;
	}
	
	public static ProductDTO createProductDTO() {
		Product product = createProduct();
		return new ProductDTO(product, product.getCategories());
	}
}
package com.book.service;

import com.book.Repository.ProductRepository;
import com.book.data.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service()
@Transactional
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public void saveProduct(Product product) {
		productRepository.saveProduct(product);
	}

	public List<Product> findAllProducts() {
		return productRepository.findAllProducts();
	}

	public void deleteProductByCode(String code) {
		productRepository.deleteProductByCode(code);
	}

	public Product findByCode(String code) {
		return productRepository.findByCode(code);
	}

	public void updateProduct(Product product){
		productRepository.updateProduct(product);
	}
}

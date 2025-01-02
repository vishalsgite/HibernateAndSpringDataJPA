package com.example.demo;

import com.example.demo.Entities.ProductEntity;
import com.example.demo.Repository.ProductRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	ProductRepo productRepo;

	@Test
	void testProductRepository() {
		ProductEntity productEntity;
        productEntity = ProductEntity.builder()
                .sku("TEST_SKU")
                .title("Test Product")
                .price(new BigDecimal("99.99"))
                .quantity(10)
                .build();

        ProductEntity savedProductEntity = productRepo.save(productEntity);
		System.out.println(savedProductEntity);
	}


	@Test
	void testGetRepo(){
		List<ProductEntity> productEntities = productRepo.findAll();
		System.out.println(productEntities);
	}

	@Test
	void testFindByTitle(){
		List<ProductEntity> productEntities = productRepo.findBytitle("Test Product");
		System.out.println(productEntities);
	}
}

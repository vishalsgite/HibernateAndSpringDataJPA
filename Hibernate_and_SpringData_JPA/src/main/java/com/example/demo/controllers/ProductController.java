package com.example.demo.controllers;

import com.example.demo.Entities.ProductEntity;
import com.example.demo.Repository.ProductRepo;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

    private final ProductRepo productRepo;

    public ProductController(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    // Endpoint to insert a new product
    @PostMapping("/insert")
    public ProductEntity insertProduct(@RequestBody ProductEntity productEntity) {
        return productRepo.save(productEntity);
    }


    @GetMapping("/findByProduct")
    public List<ProductEntity> findByProduct() {

        List<ProductEntity> products = productRepo.findByTitle("Test Product 3");
        System.out.println("Products found: " + products.size());
        return products;
    }


    @GetMapping("/getAllProducts")
    public List<ProductEntity> getAllProducts(@RequestParam(defaultValue = "Id")
                                                  Optional<String> sortBy) {

       // Sort sort = Sort.by(sortBy.orElse("Id"));
       // Sort sort = Sort.by(Sort.Direction.DESC,"price");
        String sortField = sortBy.orElse("Id");
        Sort sort = Sort.by(Sort.Direction.ASC
                ,sortField);
        return productRepo.findAll(sort);

    }

    @GetMapping("/searchProductByTitle")
    public List<ProductEntity> getProductByTitle(@RequestParam String title){
        List<ProductEntity> products = productRepo.findByTitle(title);
        System.out.println(products);
        return products;
    }
}

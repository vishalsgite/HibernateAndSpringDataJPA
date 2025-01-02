package com.example.demo.Repository;

import com.example.demo.Entities.ProductEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepo extends JpaRepository<ProductEntity,Long> {


     List<ProductEntity> findByTitle(String title);
     List<ProductEntity> findBy(Sort sort);


}

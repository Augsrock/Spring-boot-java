package com.example.casestudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.casestudy.model.Products;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Integer> {
	
	@Query("SELECT u FROM Products u WHERE u.code = :code")
	Products findProductByCode(@Param("code") String code);

}

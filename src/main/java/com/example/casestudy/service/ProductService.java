package com.example.casestudy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.casestudy.model.Products;
import com.example.casestudy.repository.ProductsRepository;

@Service
public class ProductService {
	 
	@Autowired  
	ProductsRepository productsRepository;  
	
	public List<Products> getAllProducts()   
	{  
		List<Products> product = new ArrayList<Products>();  
		productsRepository.findAll().forEach(product1 -> product.add(product1));  
		return product;  
	}  
	
	public Products getProductById(int id)   
	{  
		return productsRepository.findById(id).get();  
	}  
	
	public Products saveOrUpdate(Products product)   
	{  
		return productsRepository.save(product);  
	}  
	
	public void delete(int id)   
	{  
		productsRepository.deleteById(id);  
	}  
	
	public Products update(Products product, int productId)   
	{  
		return productsRepository.save(product);  
	}

	public Products getProductByCode(String code) {
		return productsRepository.findProductByCode(code);
	}

	public List<Products> findPaginated(int pageNo, int pageSize) {
		Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Products> pagedResult = productsRepository.findAll(paging);

        return pagedResult.toList();
	}
}

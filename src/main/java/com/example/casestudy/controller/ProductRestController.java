package com.example.casestudy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.casestudy.model.Products;
import com.example.casestudy.service.ProductService;

@RestController
@RequestMapping(value = "/api")
public class ProductRestController {
	
	@Autowired  
	ProductService productService; 
	
	/*@GetMapping("/products")  
	private List<Products> getAllProducts()   
	{  
	return productService.getAllProducts();  
	}  */
	
	@GetMapping("/products")
    public List<Products> getPaginatedProducts(@RequestParam(name="page", required = false) int page,
    		@RequestParam(name="size", required = false) int size) {
        return productService.findPaginated(page, size);
    }
	
	@GetMapping("/products/{code}")  
	private Products getProductByCode(@PathVariable("code") String code)   
	{  
		return productService.getProductByCode(code);  
	}  
	
	@DeleteMapping("/products/{code}")  
	private void deleteProduct(@PathVariable("code") String code)   
	{  
		Products product =  productService.getProductByCode(code);
		productService.delete(product.getId());  
	}   
	
	 @PostMapping("/products")
    public Products createProduct(@RequestBody Products product){
       return productService.saveOrUpdate(product);
    }
	
	@PutMapping("/products/{code}")
	public Products updateProduct(@PathVariable(value = "code") String code, @RequestBody Products productDtl) {

		Products product =  productService.getProductByCode(code);
		if(productDtl.getCode()!=null) {
			product.setCode(productDtl.getCode());
		}
		if(productDtl.getBrand()!=null) {
			product.setBrand(productDtl.getBrand());
		}
		if(productDtl.getCategory()!=null) {
			product.setCategory(productDtl.getCategory());
		}
		if(productDtl.getDescription()!=null) {
			product.setDescription(productDtl.getDescription());
		}
		if(productDtl.getName()!=null) {
			product.setName(productDtl.getName());
		}
		if(productDtl.getType()!=null) {
			product.setType(productDtl.getType());
		}

		Products updateProduct = productService.update(product, product.getId());
		return updateProduct;
	}  
}

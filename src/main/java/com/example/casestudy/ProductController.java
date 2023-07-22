package com.example.casestudy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.casestudy.model.Products;
import com.example.casestudy.repository.ProductsRepository;
import com.example.casestudy.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired  
	ProductsRepository productsRepository;  
	
	@Autowired
	ProductService productService;

	@RequestMapping(value={"/index"}, method = RequestMethod.GET)
	public ModelAndView listingPage(@ModelAttribute Products product) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("product");
		mav.addObject("products", productsRepository.findAll());
		return mav;	
	}
	
	@RequestMapping(value={"/create"}, method = RequestMethod.GET)
	public ModelAndView getSaveProduct(@ModelAttribute Products product) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("addProduct");
		mav.addObject("products", new Products());
		return mav;	
	}
	
	@RequestMapping(value={"/create"}, method = RequestMethod.POST)
	public ModelAndView postSaveProduct(@ModelAttribute Products product) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("addProduct");
		mav.addObject("products", productsRepository.save(product));
		return mav;	
	}
	
	@RequestMapping(value={"/edit"}, method = RequestMethod.GET)
	public ModelAndView editProduct(@ModelAttribute Products product, 
			@RequestParam(value = "code", required = false) String code) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("editProduct");
		
		Products productInfo = productsRepository.findProductByCode(code);
		
		mav.addObject("products", productInfo);
		return mav;	
	}
	
	@RequestMapping(value={"/update"}, method = RequestMethod.POST)
	public ModelAndView postUpdateProduct(@ModelAttribute Products product) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("editProduct");
		
		productService.update(product, product.getId());
		
		mav.addObject("products", productsRepository.save(product));
		return mav;	
	}

	
}

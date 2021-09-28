package com.coltware.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.coltware.spring.model.Product;
import com.coltware.spring.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/index")
	public ModelAndView index(@ModelAttribute Product productId, ModelAndView mav) {

		List<Product> productList = productService.getProducts(productId);

		mav.addObject("list", productList);
		mav.addObject("products", productList);

		mav.setViewName("master/product/index");

		return mav;
	}

//	@GetMapping("/index")
//	public String getIndex() {
//		return "master/product/index";
//	}
}

package com.ez.autowiredinject.controller;

import com.ez.autowiredinject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname ProductController
 * @Description TODO
 * @Author Elon.Zhang
 * @Date 2024/6/7
 */
@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@RequestMapping("/getAllProducts")
	public String getAllProducts() {

		return productService.findAllProduct();
	}
}

package com.ez.resourceinject.service;

import com.ez.resourceinject.dao.ProductDAO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @Classname ProductServiceImpl
 * @Description
 * TODO
 * @Author Elon.Zhang
 * @Date 2024/6/7
 */
@Service
public class ProductServiceImpl implements ProductService{

	// @Resource
	// private ProductDAO productDAO;

	@Override
	public String findAllProduct() {

		// return productDAO.findAllProduct();
		return "";
	}
}

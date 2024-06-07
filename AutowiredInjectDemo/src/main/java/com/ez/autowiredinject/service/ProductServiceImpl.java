package com.ez.autowiredinject.service;

import com.ez.autowiredinject.dao.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Classname ProductServiceImpl
 * @Description TODO
 * @Author Elon.Zhang
 * @Date 2024/6/7
 */
@Service
public class ProductServiceImpl implements ProductService{

	@Autowired(required=false)
	private ProductDAO productDAO;

	@Override
	public String findAllProduct() {

		return productDAO.findAllProduct();
	}
}

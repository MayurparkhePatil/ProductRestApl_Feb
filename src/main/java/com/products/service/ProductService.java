package com.products.service;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.products.entity.Product;

public interface ProductService {

	public boolean saveProduct(Product product);

	public Product getProductById(int productId);

	boolean deleteProductById(int productId);

	boolean updateProductById(Product product);

	List<Product> getAllProducts();

	List<Product> getProductsByAssending();

	List<Product> getProductByDisending();

	public Product getMaxPrizeProduct();
	
	public double countSumOfProductPrize();
	
	public long totalCountOfProduct();
	
	public int uploadFile(MultipartFile file,HttpSession session);

}

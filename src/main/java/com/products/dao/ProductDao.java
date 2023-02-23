package com.products.dao;

import java.util.List;

import com.products.entity.Product;

public interface ProductDao {

	public boolean saveProduct(Product product);

	public Product getProductById(int productId);

	public boolean deleteProductById(int productId);

	boolean updateProductById(Product product);

	List<Product> getAllProducts();

	List<Product> getProductsByAssending();

	List<Product> getProductByDisending();
	
	public Product getMaxPrizeProduct();
	
	public double countSumOfProductPrize();
	
	public long totalCountOfProduct();

	public int excelToDb(List<Product> list);
}

package com.products.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
//@Cacheable
//@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Product {
	@Id
	private int productId;
	private String productName;
	private double productCost;
	private String productCompny;
	
	public Product() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Product(int productId, String productName, double productCost, String productCompny) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productCost = productCost;
		this.productCompny = productCompny;
	}
	
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getProductCost() {
		return productCost;
	}

	public void setProductCost(double productCost) {
		this.productCost = productCost;
	}

	public String getProductCompny() {
		return productCompny;
	}

	public void setProductCompny(String productCompny) {
		this.productCompny = productCompny;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productCost=" + productCost
				+ ", productCompny=" + productCompny + "]";
	}

}

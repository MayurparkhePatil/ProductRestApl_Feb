package com.products.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.products.entity.Product;

@Repository
public class ProductDaoImpl implements ProductDao {

	@Autowired
	SessionFactory sf;
	@Override
	public boolean saveProduct(Product product) {
		Session session = sf.openSession();
		boolean isAdded=false;
		try {
				Transaction tr = session.beginTransaction();
				session.save(product);
				tr.commit();
				isAdded = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}
		return isAdded;
	}
	@Override
	public Product getProductById(int productId) {
		Product product = null;
		Session session =null;
		try {
			session = sf.openSession();
			 product = session.get(Product.class, productId);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
				
		}
		return product;
	}
	@Override
	public boolean deleteProductById(int productId) {
		 
		boolean isDeleted=false;
		Session session =null;
		try {
			
			 session =sf.openSession();
			Transaction tr = session.beginTransaction();
			Product prd=session.get(Product.class,productId );
			if(prd!=null) {
				session.delete(prd);
				tr.commit();
				isDeleted=true;
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}
		return isDeleted;
	}
	@Override
	public List<Product> getAllProducts() {
		Session session = sf.openSession();
		List list =null;
		try {
			Criteria ctr = session.createCriteria(Product.class);
			 list = ctr.list();	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(session!=null) {
				session.close();
			}
		}
		return list;
	}
	@Override
	public List<Product> getProductsByAssending() {
		
		Session session = sf.openSession();
		List list =null;
		try {
			Criteria ctr = session.createCriteria(Product.class);
			ctr.addOrder(Order.asc("productName"));
			list = ctr.list();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(session!=null) {
				session.close();
			}
		}
		
		return list;
	}
	@Override
	public List<Product> getProductByDisending() {
		Session session = sf.openSession();
		List list =null;
		try {
			Criteria ctr = session.createCriteria(Product.class);
			ctr.addOrder(Order.desc("productName"));
			list = ctr.list();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(session!=null) {
				session.close();
			}
		}
		return list;
	}
	@Override
	public boolean updateProductById(Product product) {
		Session session = sf.openSession();
		Transaction tr = session.beginTransaction();
		boolean isUpdated=false;
		try {
			Product product2 = session.get(Product.class, product.getProductId());
			session.evict(product2);
			if(product2!=null) {
				session.update(product);
				tr.commit();
				isUpdated=true;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isUpdated;
	}
	@Override
	public Product getMaxPrizeProduct() {
		Session session = sf.openSession();
		Product product2 =null;
		try {
			Criteria maxCriteria = session.createCriteria(Product.class);
			maxCriteria.setProjection(Projections.max("productCost"));
			List<Double> list = maxCriteria.list();
			Double maxPrize = list.get(0);
			
			Criteria ctr = session.createCriteria(Product.class);
			ctr.add(Restrictions.eq("productCost", maxPrize));
			List<Product> list2 = ctr.list();
			product2 = list2.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}
		return product2;
	}
	@Override
	public double countSumOfProductPrize() {
		Session session = sf.openSession();
		double sumOfPrize=0 ;
		try {
			Criteria ctr = session.createCriteria(Product.class);
			ctr.setProjection(Projections.sum("productCost"));
			List<Double> list = ctr.list();
			if(!list.isEmpty()) {
				sumOfPrize = list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}
		return sumOfPrize;
	}
	@Override
	public long totalCountOfProduct() {
		Session session = sf.openSession();
		long rowCount =0;
		try {
			Criteria ctr = session.createCriteria(Product.class);
			ctr.setProjection(Projections.rowCount());
			List<Long> list = ctr.list();
			rowCount = list.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			
			if(session!=null) {
				session.close();
			}
		}
		
		return rowCount;
	}
	@Override
	public int excelToDb(List<Product> list) {
		boolean isAdded = false;
		int addedCount=0;
		for (Product product : list) {
			isAdded = saveProduct(product);
			if(isAdded) {
				addedCount=addedCount+1;
			}
		}
		
		return addedCount;
	}

}




















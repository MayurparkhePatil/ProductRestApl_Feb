package com.products.contoller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.products.entity.Product;
import com.products.service.ProductService;

@RestController
public class ProductController {
	@Autowired
	private ProductService ps;

	@PostMapping(value = "/product/saveproduct")
	public ResponseEntity<Boolean> saveProduct(@RequestBody Product product) {
		boolean isAdded = ps.saveProduct(product);
		if (isAdded) {
			return new ResponseEntity<Boolean>(isAdded, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(isAdded, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@GetMapping(value = "/product/getproductbrid/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable int id) {
		Product product = ps.getProductById(id);
		if (product != null) {
			return new ResponseEntity<Product>(product, HttpStatus.OK);

		} else {
			return new ResponseEntity<Product>(product, HttpStatus.NOT_FOUND);

		}

	}

	@DeleteMapping(value = "/product/deleteproduct")
	public ResponseEntity<Boolean> deleteProductById(@RequestParam int id) {
		boolean isDeleted = ps.deleteProductById(id);
		if (isDeleted) {
			return new ResponseEntity<Boolean>(isDeleted, HttpStatus.OK);
		} else {
			return new ResponseEntity<Boolean>(isDeleted, HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping(value = "/product/updateProduct")
	public ResponseEntity<Boolean> updateProduct(@RequestBody Product prd) {
		boolean isUpdated = ps.updateProductById(prd);
		if (isUpdated) {
			return new ResponseEntity<Boolean>(isUpdated, HttpStatus.OK);
		} else {
			return new ResponseEntity<Boolean>(HttpStatus.NOT_ACCEPTABLE);
		}

	}

	@GetMapping(value = "/product/getallproduct")
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> list = ps.getAllProducts();
		for (Product prd : list) {
			System.out.println(prd);
		}
		return new ResponseEntity<List<Product>>(list, HttpStatus.OK);

	}

	@GetMapping(value = "/product/getproductbyassending")
	ResponseEntity<List<Product>> shortProductByAsending() {
		List<Product> list = ps.getProductsByAssending();
		if (!list.isEmpty()) {
			return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Product>>(list, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "/product/getproductbydissending")
	ResponseEntity<List<Product>> getProductByDisending() {
		List<Product> list = ps.getProductByDisending();
		if (!list.isEmpty()) {
			return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Product>>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "/product/getmaxproduct")
	ResponseEntity<Product> getMaxPrizeProduct() {
		Product maxProduct = ps.getMaxPrizeProduct();
		if(maxProduct!=null) {
			return new ResponseEntity<Product>(maxProduct, HttpStatus.OK);
		}else {
			return new ResponseEntity<Product>( HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="/product/sumofproductprize")
	ResponseEntity<Double> countSumOfProductPrize(){
		double sumOfPrize = ps.countSumOfProductPrize();
		if(sumOfPrize!=0) {
			return new ResponseEntity<Double>(sumOfPrize, HttpStatus.OK);
		}
		return new ResponseEntity<Double>(HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping(value="/product/totalCountofproduct")
	ResponseEntity<Long> totalCountOfProduct(){
		long totalCount = ps.totalCountOfProduct();
		if(totalCount!=0) {
			return new ResponseEntity<Long>(totalCount, HttpStatus.OK);
		}
		return new ResponseEntity<Long>(HttpStatus.NOT_FOUND);
	}

	@PostMapping(value="/product/fileupload")
	ResponseEntity<String>uploadFile(@RequestParam MultipartFile file,HttpSession session){
		int isAdded = ps.uploadFile(file, session);
		return new ResponseEntity<String>(isAdded+" record added", HttpStatus.OK);
	}
}













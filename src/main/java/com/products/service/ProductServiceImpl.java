package com.products.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.products.dao.ProductDao;
import com.products.entity.Product;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao pd;

	@Override
	public boolean saveProduct(Product product) {
		boolean isAdded = pd.saveProduct(product);
		return isAdded;
	}

	@Override
	public Product getProductById(int productId) {
		Product product = pd.getProductById(productId);
		return product;
	}

	@Override
	public boolean deleteProductById(int productId) {
		boolean isDeleted = pd.deleteProductById(productId);
		return isDeleted;
	}

	@Override
	public boolean updateProductById(Product product) {
		boolean isUpdated = pd.updateProductById(product);

		return isUpdated;
	}

	@Override
	public List<Product> getAllProducts() {
		List<Product> list = pd.getAllProducts();
		return list;
	}

	@Override
	public List<Product> getProductsByAssending() {
		List<Product> list = pd.getProductsByAssending();
		return list;
	}

	@Override
	public List<Product> getProductByDisending() {
		List<Product> list = pd.getProductByDisending();

		return list;
	}

	@Override
	public Product getMaxPrizeProduct() {
		Product maxProduct = pd.getMaxPrizeProduct();
		return maxProduct;
	}

	@Override
	public double countSumOfProductPrize() {
		double sumOfPrize = pd.countSumOfProductPrize();
		return sumOfPrize;
	}

	@Override
	public long totalCountOfProduct() {
		long totalCount = pd.totalCountOfProduct();
		return totalCount;
	}

	public List<Product>readExcel(String path){
		
		
		List<Product>list=new ArrayList<>();
		Product prd=null;
		Workbook wb=new XSSFWorkbook();
		Sheet sheet = wb.getSheetAt(0);
		Iterator<Row> rows = sheet.rowIterator();
		int count=0;
		
		while (rows.hasNext()) {
			Row row = rows.next();
			prd=new Product();
			if(count==0) {
				count++;
				continue;
			}
			
			
			Iterator<Cell> cells = row.cellIterator();
			while(cells.hasNext()) {
				Cell cell = cells.next();
				int col = cell.getColumnIndex();
				switch(col) {
				case 1:{
					prd.setProductId((int)cell.getNumericCellValue());
					break;
				}
				case 2:{
					prd.setProductName(cell.getStringCellValue());
					break;
				}
				case 3:{
					prd.setProductCost(cell.getNumericCellValue());
					break;
				}
				case 4:{
					prd.setProductCompny(cell.getStringCellValue());
					break;
				}
				default:
					break;
				}
			}
			list.add(prd);
		}
		return list;
	}

	@Override
	public int uploadFile(MultipartFile file, HttpSession session) {
		int isAdded =0;
		String fileName = file.getOriginalFilename();
		String path = session.getServletContext().getRealPath("/");
		try (FileOutputStream fos = new FileOutputStream(new File(path + File.separator + fileName))) {
			byte[] data = file.getBytes();
			fos.write(data);
			List<Product> list = readExcel(path + File.separator + fileName);
			
			isAdded = pd.excelToDb(list);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isAdded;
	}

}

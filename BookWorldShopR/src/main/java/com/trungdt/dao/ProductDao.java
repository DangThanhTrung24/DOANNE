package com.trungdt.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.trungdt.entity.Product;
import com.trungdt.model.StatisticalProductDay;

/**
 * Class thuc hien truy van thong tin bang Product trong database
 */
public interface ProductDao extends JpaRepository<Product, Integer>{
	// tìm danh sách taatsca sản phẩm
	@Query("SELECT p FROM Product p WHERE p.Deleteday = null")
	List<Product> getListProduct();
	
	//chọn 12 sản phẩm mới nhất tính theo ngày thêm
	@Query(value="SELECT TOP(12) * FROM Products WHERE DeleteDay is NULL and Active = 1 ORDER BY CreateDay DESC", nativeQuery = true)
	List<Product> getListLatestProduct();
	
	// chọn 14 sản phẩm nhiều view nhất
	@Query(value="SELECT TOP(14) * FROM Products WHERE DeleteDay is NULL and Active = 1 ORDER BY Views DESC", nativeQuery = true)
	List<Product> getListViewsProduct();
	
	@Query("SELECT p FROM Product p WHERE p.category.Namesearch LIKE ?1 AND p.Deleteday = null AND p.active = 1 ORDER BY p.Createday DESC")
	Page<Product> getListProductByNameSearch(String nameSearch, Pageable pageable);
	
	@Query("SELECT p FROM Product p WHERE p.category.Namesearch LIKE ?1 AND p.Deleteday = null AND p.active = 1 AND p.price >= ?2 AND p.price <= ?3 ORDER BY p.Createday DESC")
	Page<Product> getListProductByPrice(String nameSearch, int minPrice, int maxPrice, Pageable pageable);
	
	@Query("SELECT p FROM Product p WHERE p.category.Namesearch LIKE ?1 AND p.Deleteday = null")
	List<Product> getListDemo(String nameSearch);
	
	// chọn sản phẩm được truyền vào bằng Namesearch
	@Query("SELECT p FROM Product p WHERE p.Deleteday = null AND p.Namesearch LIKE ?1")
	Product getProductByNameSearch(String nameSearch);
	
	//12 sản phẩm cùng loại
	@Query(value="SELECT TOP(12) * FROM Products WHERE DeleteDay is NULL and Active = 1 and Cate_Id = ?1 ORDER BY Views DESC", nativeQuery = true)
	List<Product> getListProductRelated(int manuId);
	
	// 5 sản phẩm đang sale
	@Query(value="SELECT TOP(5) * FROM Products WHERE DeleteDay is NULL and Active = 1 and Sales != 0 ORDER BY Views DESC", nativeQuery = true)
	List<Product> getListProductSales();
	
	
	// test sản phẩm tồn kho
	//trả về tất cả thông tin về các sản phẩm (ProductS) mà không có trong bảng đơn đặt hàng (ORDERS) và đồng thời chưa bị xóa.
	@Query(value="SELECT * FROM ProductS WHERE NOT EXISTS (SELECT * FROM ORDERS WHERE Products.Id = ORDERS.Product_Id) AND ProductS.DeleteDay is NULL", nativeQuery = true)
	List<Product> listStatisticalProductWarehouse();
	
//	@Query(value="SELECT * FROM ProductS WHERE Quality > 0 AND DeleteDay is NULL", nativeQuery = true)
//	List<Product> listStatisticalProductWarehouse();
}

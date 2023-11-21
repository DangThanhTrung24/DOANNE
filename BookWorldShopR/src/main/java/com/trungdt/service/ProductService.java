package com.trungdt.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.trungdt.entity.Product;
import com.trungdt.model.ProductModel;
import com.trungdt.model.ShowProduct;

/**
 * Class cung cap cac dich vu thao tac voi table Products trong database
 */
public interface ProductService {

	// thêm một sản phẩm mới (admin)
	ProductModel createProduct(ProductModel productModel);

	// danh sách sản phẩm
	List<Product> findAll();

	// xóa sản phẩm (admin)
	void delete(Integer id);

	// cập nhật thông tin sản phẩm (admin)
	ProductModel updateProduct(ProductModel productModel);

	// thông tin của sản phẩm nhất định
	ProductModel getOneProductById(Integer id);

	// 12 sản phẩm mới nhất
	List<Product> getListLatestProduct();

	// 14 sản phẩm nhiều view nhất
	List<Product> getListViewsProduct();

	Page<Product> getListProductByNameSearch(String nameSearch, Pageable pageable);

	List<Product> getDemo(String nameSearch);

	Page<Product> getListProductByPrice(String nameSearch, int minPrice, int maxPrice, Pageable pageable);

	// lọc sản phẩm theo các yếu tố
	Page<ShowProduct> getListProductByFilter(String nameSearch, String price, String manu, String sort, Pageable pageable, String status, String name, String category);

	// lấy sản phẩm dựa trên tên sản phẩm
	Product getProductByNameSearch(String nameSearch);

	// danh sách sản phẩm cùng loại
	List<Product> getListProductRelated(int id);

	// cập nhật lượt xem khi click vào sản phẩm
	void updateView(String nameSearch);

	// update số lượng khi đặt hàng
	void updateQuality(Product product);

	// danh sách 5 sản phẩm đang sale
	List<Product> getListProductSales();

}

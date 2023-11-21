package com.trungdt.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trungdt.entity.Product;
import com.trungdt.model.ProductModel;
import com.trungdt.service.ProductService;

/**
 * Class cung cap cac dich vu rest api cho bang product
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/rest/product")
public class ProductRestController {
	@Autowired
	ProductService productService;
	
	// thêm một sản phẩm mới (admin)
	@PostMapping("/form")
	public ProductModel create(@RequestBody ProductModel productModel) {
		return productService.createProduct(productModel);
	}
	
	// hiển thị tất cả sản phẩm lên form (admin)
	@GetMapping()
	public List<Product> getAll(){
		return productService.findAll();
	}
	
	// xóa sản phẩm (admin)
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		productService.delete(id);
	}
	
	// cập nhật thông tin sản phẩm (admin)
	@PutMapping("/form/{id}")
	public ProductModel update(@PathVariable("id") Integer id, @RequestBody ProductModel productModel) {
		return productService.updateProduct(productModel);
	}
	
	// hiển thị chi tiết sản phẩm lên form (admin)
	@GetMapping("/form/{id}")
	public ProductModel getOneProductById(@PathVariable("id") Integer id) {
		return productService.getOneProductById(id);
	}
}

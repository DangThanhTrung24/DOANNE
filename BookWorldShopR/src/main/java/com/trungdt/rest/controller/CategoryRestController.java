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

import com.trungdt.entity.Category;
import com.trungdt.model.CategoryModel;
import com.trungdt.service.CategoryService;

/**
 * Class cung cap cac dich vu rest api cho bang employee
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/rest/categories")
public class CategoryRestController {
	@Autowired
	CategoryService categoryService;
	
	// thêm danh mục (admin)
	@PostMapping("/form")
	public CategoryModel create(@RequestBody CategoryModel categoryModel) {
		return categoryService.createCategory(categoryModel);
	}
	
	// hiển thị tất cả loại trang admin
	@GetMapping()
	public List<Category> getAll(){
		return categoryService.findAll();
	}
	
	// hiển thị thông tin danh mục được chọn (admin)
	@GetMapping("/form/{id}")
	public CategoryModel getOneUserById(@PathVariable("id") Integer id) {
		return categoryService.getOneCategoryById(id);
	}
	
	// xóa loại danh mục (admin)
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		categoryService.delete(id);
	}
	
	// cập nhật loại danh mục (admin)
	@PutMapping("/form/{id}")
	public CategoryModel update(@PathVariable("id") Integer id, @RequestBody CategoryModel categoryModel) {
		return categoryService.updateCategory(categoryModel);
	}
}

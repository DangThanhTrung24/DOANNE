package com.trungdt.service;

import java.util.List;

import com.trungdt.entity.Category;
import com.trungdt.model.CategoryModel;

public interface CategoryService {

	// thêm loại danh mục (admin)
	CategoryModel createCategory(CategoryModel categoryModel);

	// lấy tất cả các loại sản phẩm
	List<Category> findAll();

	// xóa loại danh mục (admin)
	void delete(Integer id);

	// thông tin loại danh mục (admin)
	CategoryModel getOneCategoryById(Integer id);

	// cập nhật loại danhmuc (admin)
	CategoryModel updateCategory(CategoryModel categoryModel);

	// lấy loại sản phẩm
	Category getCategoryByNameSearch(String nameSearch);

}

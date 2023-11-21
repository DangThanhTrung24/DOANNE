package com.trungdt.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.trungdt.dao.CategoryDao;
import com.trungdt.dao.UserDao;
import com.trungdt.entity.Category;
import com.trungdt.entity.User;
import com.trungdt.model.CategoryModel;
import com.trungdt.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	CategoryDao categoryDao;
	
	@Autowired
	UserDao userDao;
	
	// thêm loại danh mục (admin)
	@Override
	public CategoryModel createCategory(CategoryModel categoryModel) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		User temp = userDao.findUserByEmail(username);
		
		Category category = new Category();
		category.setName(categoryModel.getName());
		category.setNamesearch(categoryModel.getNameSearch());
		category.setLogo(categoryModel.getLogo());
		category.setBanner(categoryModel.getBanner());
		category.setDescription(categoryModel.getDescribe());
		category.setPersoncreate(temp.getId());
		category.setCreateday(timestamp.toString());
		// lưu danh mục vào db với người tạo ngày tạo và các thuộc tính còn lại
		categoryDao.save(category);
		return categoryModel;
	}

	// tất cả loại sản phẩm
	@Override
	public List<Category> findAll() {
		return categoryDao.getListCategory();
	}

	// xóa loại danh mục (admin)
	@Override
	public void delete(Integer id) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		User temp = userDao.findUserByEmail(username);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		Category category = categoryDao.findById(id).get();
		category.setPersondelete(temp.getId());
		category.setDeleteday(timestamp.toString());
		// lưu người xóa ngày xóa và các thuộc tính còn lại
		categoryDao.save(category);
	}

	// thông tin loại danh mục đucợ chọn(admin)
	@Override
	public CategoryModel getOneCategoryById(Integer id) {
		Category category = categoryDao.findById(id).get();
		CategoryModel categoryModel = new CategoryModel();
		categoryModel.setName(category.getName());
		categoryModel.setNameSearch(category.getNamesearch());
		categoryModel.setLogo(category.getLogo());
		categoryModel.setBanner(category.getBanner());
		categoryModel.setDescribe(category.getDescription());
		return categoryModel;
	}

	// cập nhật loại danh mục (admin)
	@Override
	public CategoryModel updateCategory(CategoryModel categoryModel) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		User temp = userDao.findUserByEmail(username);
		
		Category category = categoryDao.findById(categoryModel.getId()).get();
		category.setName(categoryModel.getName());
		category.setNamesearch(categoryModel.getNameSearch());
		category.setLogo(categoryModel.getLogo());
		category.setBanner(categoryModel.getBanner());
		category.setDescription(categoryModel.getDescribe());
		category.setUpdateday(timestamp.toString());
		category.setPersonupdate(temp.getId());
		// lưu người cập nhật hiện tại và ngày và các thuộc tính khác
		categoryDao.save(category);
		return categoryModel;
	}

	// lấy loại sản phẩm được truyền vào
	@Override
	public Category getCategoryByNameSearch(String nameSearch) {
		return categoryDao.getCategoryByNameSearch(nameSearch);
	}
}

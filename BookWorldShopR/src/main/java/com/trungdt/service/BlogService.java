package com.trungdt.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.trungdt.entity.Blog;
import com.trungdt.model.BlogModel;

public interface BlogService {

	// thêm mới blog (admin)
	BlogModel createBlog(BlogModel blogModel);
	
	// hiển thị blog (admin)
	List<Blog> findAll();

	// xóa blog (admin)
	void delete(Integer id);

	// tìm blog dựa vào id
	BlogModel getOneBlogById(Integer id);

	// cập nhật blog (admin)
	BlogModel updateCategory(BlogModel blogModel);

	Blog findById(Integer id);

	// danh sách blog đang hoạt động
	Page<Blog> findAllBlogActive(Pageable pageable);

	// thông tin blog dựa trên namesearch
	Blog findBlogByNameSearch(String nameSearch);

	// lấy 6 bài blog
	List<Blog> getSixBlog();

}

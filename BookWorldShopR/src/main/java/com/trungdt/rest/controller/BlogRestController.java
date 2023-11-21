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

import com.trungdt.entity.Blog;
import com.trungdt.model.BlogModel;
import com.trungdt.service.BlogService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/blog")
public class BlogRestController {
	@Autowired
	BlogService blogService;
	
	// thêm mới blog (admin)
	@PostMapping("/form")
	public BlogModel create(@RequestBody BlogModel blogModel) {
		return blogService.createBlog(blogModel);
	}
	// hiển thị blog (admin)
	@GetMapping()
	public List<Blog> getAll(){
		return blogService.findAll();
	}
	
	// hiển thị trang update blog
	@GetMapping("/form/{id}")
	public BlogModel getOneUserById(@PathVariable("id") Integer id) {
		return blogService.getOneBlogById(id);
	}
	
	// xóa blog (admin)
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		blogService.delete(id);
	}
	
	// cập nhật blog (admin)
	@PutMapping("/form/{id}")
	public BlogModel update(@PathVariable("id") Integer id, @RequestBody BlogModel blogModel) {
		return blogService.updateCategory(blogModel);
	}
}

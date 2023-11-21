package com.trungdt.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.trungdt.dao.BlogDao;
import com.trungdt.dao.UserDao;
import com.trungdt.entity.Blog;
import com.trungdt.entity.User;
import com.trungdt.model.BlogModel;
import com.trungdt.service.BlogService;

@Service
@Repository
public class BlogServiceImpl implements BlogService {
	@Autowired
	BlogDao dao;
	
	@Autowired
	UserDao userDao;
	
	// thêm mới blog (admin)
	@Override
	public BlogModel createBlog(BlogModel blogModel) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		User temp = userDao.findUserByEmail(username);
		
		Blog blog = new Blog();
		blog.setTitle(blogModel.getTitle());
		blog.setLogo(blogModel.getLogo());
		blog.setContent(blogModel.getContent());
		blog.setBanner(blogModel.getBanner());
		blog.setUploadday(blogModel.getUploadDay());
		blog.setPersoncreate(temp.getId());
		blog.setCreateday(timestamp.toString());
		blog.setActive(blogModel.isActive());
		blog.setDescription(blogModel.getDescription());
		blog.setNamesearch(blogModel.getNameSearch());
		dao.save(blog);
		return blogModel;
	}

	// hiển thị blog (admin)
	@Override
	public List<Blog> findAll() {
		return dao.getListBlog();
	}

	// xóa blog admin
	@Override
	public void delete(Integer id) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		User temp = userDao.findUserByEmail(username);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		Blog blog = dao.findById(id).get();
		blog.setPersondelete(temp.getId());
		blog.setDeleteday(timestamp.toString());
		// lưu người xóa và thời gian xóa
		dao.save(blog);
	}

	// hiển thị thông tin blog dựa vào id
	@Override
	public BlogModel getOneBlogById(Integer id) {
		Blog blog = dao.findById(id).get();
		BlogModel blogModel = new BlogModel();
		blogModel.setTitle(blog.getTitle());
		blogModel.setLogo(blog.getLogo());
		blogModel.setContent(blog.getContent());
		blogModel.setBanner(blog.getBanner());
		blogModel.setUploadDay(blog.getUploadday());
		blogModel.setDescription(blog.getDescription());
		blogModel.setNameSearch(blog.getNamesearch());
		blog.setActive(blogModel.isActive());
		return blogModel;
	}

	// cập nhật blog (admin)
	@Override
	public BlogModel updateCategory(BlogModel blogModel) {
		// thông tin người dùng hiện tại
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		User temp = userDao.findUserByEmail(username);
		
		Blog blog = dao.findById(blogModel.getId()).get();
		blog.setTitle(blogModel.getTitle());
		blog.setLogo(blogModel.getLogo());
		blog.setContent(blogModel.getContent());
		blog.setBanner(blogModel.getBanner());
		blog.setUploadday(blogModel.getUploadDay());
		blog.setUpdateday(timestamp.toString());
		blog.setPersonupdate(temp.getId());
		blog.setActive(blogModel.isActive());
		blog.setDescription(blogModel.getDescription());
		blog.setNamesearch(blogModel.getNameSearch());
		dao.save(blog);
		return blogModel;
	}

	@Override
	public Blog findById(Integer id) {
		return dao.findById(id).get();
	}

	// danh sách blog
	@Override
	public Page<Blog> findAllBlogActive(Pageable pageable) {
		Page<Blog> listBlog = dao.findAllBlogActive(pageable);
		return listBlog;
	}

	// chi tiết blog
	@Override
	public Blog findBlogByNameSearch(String nameSearch) {
		Blog blog = dao.findBlogByNameSearch(nameSearch);
		return blog;
	}

	// lấy 6 bài blog
	@Override
	public List<Blog> getSixBlog() {
		List<Blog> listBlog = dao.getSixBlogs();
		return listBlog;
	}
	
}

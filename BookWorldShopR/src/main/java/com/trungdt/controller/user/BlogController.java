package com.trungdt.controller.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.trungdt.common.Constants;
import com.trungdt.entity.Blog;
import com.trungdt.entity.Product;
import com.trungdt.entity.User;
import com.trungdt.model.BestSellerModel;
import com.trungdt.model.ShowProduct;
import com.trungdt.service.BlogService;
import com.trungdt.service.CommentService;
import com.trungdt.service.OrderService;
import com.trungdt.service.ParamService;
import com.trungdt.service.ProductService;
import com.trungdt.service.UserService;

@Controller
public class BlogController {
	@Autowired
	BlogService blogService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ParamService paramService;
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	ProductService productService;

	// hiển thị danh sách blog
	@GetMapping("/blog")
	public String index(Model model, @RequestParam("p") Optional<Integer> p) {
		// hiển thị 2 blog phân trang
		Pageable pageable = PageRequest.of(p.orElse(0), 2);
		
		// danh sách blog
		Page<Blog> list = blogService.findAllBlogActive(pageable);
		
		for(Blog blog: list) {
			// format ngày tháng năm
			String uploadDay = paramService.convertDate(blog.getUploadday());
			blog.setUploadday(uploadDay);
		}
		model.addAttribute("blogList", list);
		return Constants.USER_DISPLAY_BLOG;
	}

	// chi tiết blog
	@GetMapping("/blog/{nameSearch}")
	public String index(Model model, @PathVariable("nameSearch") String nameSearch) {
		// thông tin blog dựa trên nameSearch
		Blog blog = blogService.findBlogByNameSearch(nameSearch);
		// thông tin người tạo ra blog 
		User user = userService.findById(blog.getPersoncreate());
		
		// format ngày tháng năm
		String uploadDay = paramService.convertDate(blog.getUploadday());
		
		blog.setUploadday(uploadDay);
		
		System.out.println(blog.getUploadday());
		
		model.addAttribute("blogInfor", blog);
		model.addAttribute("blogUser", user);
		return Constants.USER_DISPLAY_BLOG_DETAIL;
	}
	
}

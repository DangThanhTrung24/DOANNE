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
import com.trungdt.entity.Category;
import com.trungdt.entity.Manufacturer;
import com.trungdt.model.BestSellerModel;
import com.trungdt.model.ShowProduct;
import com.trungdt.service.CategoryService;
import com.trungdt.service.CommentService;
import com.trungdt.service.ManufacturerService;
import com.trungdt.service.OrderService;
import com.trungdt.service.ProductService;
import com.trungdt.service.SessionService;

/**
 * Class de danh sach san pham
 */
@Controller
public class ListProductController {
	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	SessionService sessionService;

	@Autowired
	ManufacturerService manufacturerService;

	@Autowired
	OrderService orderService;

	@Autowired
	CommentService commentService;

	// hiển thích sản phẩm theo loại
	@GetMapping("/products/{nameSearch}")
	public String index(@PathVariable("nameSearch") String nameSearch, Model model,
			@RequestParam("page") Optional<Integer> p, @RequestParam(name = "price", required = false) String price,
			@RequestParam(name = "manuF", required = false) String manu,
			@RequestParam(name = "sort", required = false) String sort) {
		try {
			Pageable pageable = PageRequest.of(p.orElse(0), 9);

			// Lấy danh sách sản phẩm dựa trên các bộ lọc và thông số truyền vào
			Page<ShowProduct> listProduct = productService.getListProductByFilter(nameSearch, price, manu, sort,
					pageable, "products", "", "");
			 // Đặt các thông tin sản phẩm vào model để sử dụng trong giao diện
			model.addAttribute("listProduct", listProduct);
			model.addAttribute("price", price);
			model.addAttribute("manuF", manu);
			model.addAttribute("sort", sort);
			model.addAttribute("nameSearch", nameSearch);

			// Lấy thông tin danh mục từ tên tìm kiếm và đặt vào model
			Category category = categoryService.getCategoryByNameSearch(nameSearch);
			model.addAttribute("inforCategory", category);
		} catch (Exception e) {
			return "redirect:/index";
		}

		return Constants.USER_DISPLAY_LIST_PRODUCT_BY_CATEGORY;
	}

	// hiển thị trang products
	@GetMapping("/products")
	public String sales(Model model, @RequestParam("page") Optional<Integer> p,
			@RequestParam(name = "price", required = false) String price,
			@RequestParam(name = "manuF", required = false) String manu,
			@RequestParam(name = "sort", required = false) String sort) {

		Pageable pageable = PageRequest.of(p.orElse(0), 9);
		// Lấy danh sách sản phẩm dựa trên các bộ lọc và thông số truyền vào
		Page<ShowProduct> listProduct = productService.getListProductByFilter("", price, manu, sort, pageable, "products",
				"", "");
		model.addAttribute("listProduct", listProduct);
		model.addAttribute("price", price);
		model.addAttribute("manuF", manu);
		model.addAttribute("sort", sort);

		return Constants.USER_DISPLAY_LIST_PRODUCT_BY_SALES;
	}

	// hiển thị sản phẩm theo search
	@GetMapping("/search")
	public String searcch(Model model, @RequestParam(name = "namePrd", required = false) String name,
			@RequestParam("page") Optional<Integer> p, @RequestParam(name = "price", required = false) String price,
			@RequestParam(name = "manuF", required = false) String manu,
			@RequestParam(name = "sort", required = false) String sort,
			@RequestParam(name = "category", required = false) String category) {
		try {
			Pageable pageable = PageRequest.of(p.orElse(0), 5);

			if (category == null) {
				category = "";
			}

			if (name == null) {
				name = "";
			}
			// Lấy danh sách sản phẩm dựa trên các bộ lọc và thông số truyền vào
			Page<ShowProduct> listProduct = productService.getListProductByFilter("", price, manu, sort, pageable,
					"search", name.trim(), category);
			if (!name.trim().isEmpty()) {
				model.addAttribute("title", "- " + name.trim());
				model.addAttribute("name", name.trim());
			}
			model.addAttribute("cate", category);
			model.addAttribute("listProduct", listProduct);
			model.addAttribute("price", price);
			model.addAttribute("manuF", manu);
			model.addAttribute("sort", sort);

			// lấy danh sách tất cả loại sản phẩm
			List<Category> listCategory = categoryService.findAll();
			model.addAttribute("listCategory", listCategory);

		} catch (Exception e) {
			return "redirect:/index";
		}

		return Constants.USER_DISPLAY_LIST_PRODUCT_BY_SEARCH;
	}

	// danh sách nhà cung cấp
	@ModelAttribute("listManu")
	public List<Manufacturer> listManu() {
		List<Manufacturer> list = manufacturerService.findAll();
		return list;
	}

	// hiển thị danh sách bestseller
	@ModelAttribute("listBestSeller")
	public List<ShowProduct> getListBestSeller(Model model) {
		Pageable topFive = PageRequest.of(0, 5);

		List<BestSellerModel> list = orderService.getListBestSellerProduct(topFive);

		List<ShowProduct> listProduct = new ArrayList<ShowProduct>();

		for (BestSellerModel bestSeller : list) {
			ShowProduct showProduct = new ShowProduct();
			int totalStar = commentService
					.getAllStarCommentByProductNameSearch(bestSeller.getProduct().getNamesearch());
			showProduct.setProduct(bestSeller.getProduct());
			showProduct.setTotalStar(totalStar);
			listProduct.add(showProduct);
		}
		return listProduct;
	}
}

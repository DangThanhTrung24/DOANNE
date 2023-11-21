package com.trungdt.controller.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.trungdt.common.Constants;
import com.trungdt.entity.Blog;
import com.trungdt.entity.Manufacturer;
import com.trungdt.entity.Product;
import com.trungdt.model.BestSellerModel;
import com.trungdt.model.ShowProduct;
import com.trungdt.service.BlogService;
import com.trungdt.service.CommentService;
import com.trungdt.service.FavoriteService;
import com.trungdt.service.ManufacturerService;
import com.trungdt.service.OrderService;
import com.trungdt.service.ParamService;
import com.trungdt.service.ProductService;
import com.trungdt.service.UserRoleService;

/**
 * Class de hien thi trang chu nguoi dung
 */
@Controller
public class IndexController {
	@Autowired
	UserRoleService userRoleService;

	@Autowired
	ProductService productService;

	@Autowired
	ManufacturerService manufacturerService;

	@Autowired
	CommentService commentService;

	@Autowired
	BlogService blogService;

	@Autowired
	ParamService paramService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	FavoriteService favoriteService;

	/**
	 * Hien thi trang chu cua giao dien nguoi dung
	 * 
	 * @return trang index.html
	 */
	@GetMapping({ "/", "/index" })
	public String index(Model model) {
		return Constants.USER_DISPLAY_INDEX;
	}

	//hiển thị nhà cung cấp
	@ModelAttribute("manufacturer")
	public List<Manufacturer> manufacture(Model model) {
		List<Manufacturer> list = manufacturerService.findAll();
		return list;
	}

	//Sản phẩm mới nhất
	@ModelAttribute("latestProduct")
	public List<List<ShowProduct>> getLatestProduct(Model model) {
		// Lấy danh sách sản phẩm mới nhất
		List<Product> list = productService.getListLatestProduct();

		// Khởi tạo một danh sách tạm thời để lưu các đối tượng ShowProduct
		List<ShowProduct> temp = new ArrayList<>();
		
	    // Khởi tạo kết quả cuối cùng là một danh sách các danh sách các đối tượng ShowProduct
		List<List<ShowProduct>> result = new ArrayList<List<ShowProduct>>();

		// Duyệt qua danh sách các sản phẩm
		for (int i = 0; i < list.size(); i++) {
			// Lấy tổng số sao cho một sản phẩm dựa trên tìm kiếm theo tên
			int totalStar = commentService.getAllStarCommentByProductNameSearch(list.get(i).getNamesearch());
			// Tạo một đối tượng ShowProduct
			ShowProduct showProduct = new ShowProduct();
			showProduct.setProduct(list.get(i));
			showProduct.setTotalStar(totalStar);
			// Thêm đối tượng ShowProduct vào danh sách tạm thời
			// Kiểm tra nếu chỉ số hiện tại là số lẻ
			temp.add(showProduct);
			if (i % 2 != 0) {
				// Nếu là số lẻ, thêm danh sách tạm thời vào kết quả cuối cùng
				result.add(temp);
				// Đặt lại danh sách tạm thời để bắt đầu một cặp sản phẩm mới
				temp = new ArrayList<>();
			}
			// Kiểm tra xem đây có phải sản phẩm cuối cùng trong danh sách không
			if (i == (list.size() - 1)) {
				// Nếu tổng số sản phẩm là số lẻ, thêm sản phẩm còn lại trong danh sách tạm thời vào kết quả cuối cùng
				if (i % 2 == 0) {
					result.add(temp);
					temp = new ArrayList<>();
				}
			}
		}

		return result;
	}

	// Sản phẩm nổi bật(nhiều view nhất)
	@ModelAttribute("featuredProduct")
	public List<ShowProduct> getViewsProduct(Model model) {
		// Lấy danh sách các sản phẩm theo lượt xem
		List<Product> list = productService.getListViewsProduct();
		// Khởi tạo danh sách để lưu thông tin hiển thị sản phẩm
		List<ShowProduct> listProduct = new ArrayList<ShowProduct>();

		for (Product product : list) {
			ShowProduct showProduct = new ShowProduct();
			// Lấy tổng số sao cho sản phẩm dựa trên tên sản phẩm
			int totalStar = commentService.getAllStarCommentByProductNameSearch(product.getNamesearch());
			showProduct.setProduct(product);
			showProduct.setTotalStar(totalStar);
			 // Thêm đối tượng ShowProduct vào danh sách hiển thị sản phẩm
			listProduct.add(showProduct);
		}

		return listProduct;
	}

	// hiển thị blog
	@ModelAttribute("listBlog")
	public List<Blog> getListBlog(Model model) {
		//lấy danh sách 6 bài blog gần nhất
		List<Blog> listBlog = blogService.getSixBlog();
		for (Blog blog : listBlog) {
			String uploadDay = paramService.convertDate(blog.getUploadday());
			blog.setUploadday(uploadDay);
		}
		return listBlog;
	}

	//sản phẩm khuyến mãi(hot promotional) 5 sản phẩm
	@ModelAttribute("listSale")
	public List<ShowProduct> getListProductSale(Model model) {
		// danh sách sản phẩm đang sale
		List<Product> list = productService.getListProductSales();

		List<ShowProduct> listProduct = new ArrayList<ShowProduct>();

		for (Product product : list) {
			ShowProduct showProduct = new ShowProduct();
			int totalStar = commentService.getAllStarCommentByProductNameSearch(product.getNamesearch());
			showProduct.setProduct(product);
			showProduct.setTotalStar(totalStar);
			listProduct.add(showProduct);
		}

		return listProduct;
	}
	
	//5 sản phẩm bán được nhiều nhất(bestseller)
	@ModelAttribute("listBestSeller")
	public List<ShowProduct> getListBestSeller(Model model){
		Pageable topFive = PageRequest.of(0, 5);
		// lấy danh sách sản phẩm bán nhiều nhất
		List<BestSellerModel> list = orderService.getListBestSellerProduct(topFive);
		
		List<ShowProduct> listProduct = new ArrayList<ShowProduct>();
		
		for(BestSellerModel bestSeller: list) {
			ShowProduct showProduct = new ShowProduct();
			int totalStar = commentService.getAllStarCommentByProductNameSearch(bestSeller.getProduct().getNamesearch());
			showProduct.setProduct(bestSeller.getProduct());
			showProduct.setTotalStar(totalStar);
			listProduct.add(showProduct);
		}		
		return listProduct;
	}
	
	// 5 sản phẩm được yêu thích nhất
	@ModelAttribute("listFavorite")
	public List<ShowProduct> demo(Model model) {
		Pageable topFive = PageRequest.of(0, 5);
		// lấy danh sách sản phẩm được yêu thích nhất
		List<BestSellerModel> list = favoriteService.getListBestSellerProduct(topFive);
		
		List<ShowProduct> listProduct = new ArrayList<ShowProduct>();
		
		for(BestSellerModel bestSeller: list) {
			ShowProduct showProduct = new ShowProduct();
			int totalStar = commentService.getAllStarCommentByProductNameSearch(bestSeller.getProduct().getNamesearch());
			showProduct.setProduct(bestSeller.getProduct());
			showProduct.setTotalStar(totalStar);
			listProduct.add(showProduct);
		}		
		return listProduct;
	}

}

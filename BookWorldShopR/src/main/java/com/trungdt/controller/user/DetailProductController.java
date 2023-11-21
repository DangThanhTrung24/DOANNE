package com.trungdt.controller.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.trungdt.common.Constants;
import com.trungdt.entity.Product;
import com.trungdt.model.BestSellerModel;
import com.trungdt.model.CartModel;
import com.trungdt.model.ShowProduct;
import com.trungdt.service.CategoryService;
import com.trungdt.service.CommentService;
import com.trungdt.service.FavoriteService;
import com.trungdt.service.OrderService;
import com.trungdt.service.ProductService;
import com.trungdt.service.SessionService;
import com.trungdt.service.impl.ShoppingCartServiceImpl;

@Controller
public class DetailProductController {
	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;
	
	@Autowired
	SessionService sessionService;
	
	@Autowired
	CommentService commentService;

	@Autowired
	ShoppingCartServiceImpl cartService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	FavoriteService favoriteService;

	// hiển thị chi tiết sản phẩm
	@GetMapping("/product/{nameSearch}")
	public String index(@PathVariable("nameSearch") String nameSearch, Model model) {
		// thêm 1 lượt xem
		productService.updateView(nameSearch);
		model.addAttribute("infor", false);
		sessionService.set("sessionProduct", cartService);
		return Constants.USER_DISPLAY_DETAIL_PRODUCT;
	}
	
	// thêm sản phẩm vào giỏ hàng
	@SuppressWarnings("static-access")
	@PostMapping("/product/{nameSearch}")
	public String orderProduct(@PathVariable("nameSearch") String nameSearch, Model model, HttpServletRequest req) {
		// Lấy thông tin sản phẩm dựa trên tên tìm kiếm
		Product product = productService.getProductByNameSearch(nameSearch);
		// Lấy bản đồ (Map) chứa thông tin giỏ hàng từ dịch vụ cartService	
		Map<Integer, CartModel> map = cartService.map;
		// Lấy thông tin sản phẩm trong giỏ hàng (nếu có) dựa trên ID sản phẩm
		CartModel cartModel = map.get(product.getId());
		// Nếu sản phẩm chưa có trong giỏ hàng
		if(cartModel == null) {
			// Tạo một CartModel mới
			cartModel = new CartModel();
			cartModel.setId(product.getId());
			cartModel.setProduct(product);
			cartModel.setQuality(1);
			// Thêm sản phẩm vào giỏ hàng
			cartService.add(product.getId(), cartModel);
		}
		
		else {
			// Nếu sản phẩm đã có trong giỏ hàng, cập nhật số lượng
			cartService.update(cartModel.getId(), cartModel.getQuality() + 1);
		}
		// Đánh dấu rằng thao tác đã thành công để thông báo tới người dùng
		model.addAttribute("infor", true);
		// Lưu thông tin giỏ hàng vào session
		sessionService.set("sessionProduct", cartService);
		
		return Constants.USER_DISPLAY_DETAIL_PRODUCT;
	}

	// hiển thị thông tin sản phẩm
	@ModelAttribute("inforProduct")
	public Product inforCategory(@PathVariable("nameSearch") String nameSearch) {
		Product product = productService.getProductByNameSearch(nameSearch);
		return product;
	}

	// danh sách sản phẩm liên quan
	@ModelAttribute("listProductRelated")
	public List<ShowProduct> listProductRelated(@PathVariable("nameSearch") String nameSearch) {
		// thông tin sản phẩm được truyền vào
		Product product = productService.getProductByNameSearch(nameSearch);
		// danh sách sản phẩm liên quan(cùng loại)
		List<Product> list = productService.getListProductRelated(product.getCategory().getId());

		List<ShowProduct> listProduct = new ArrayList<ShowProduct>();

		for (Product item : list) {
			ShowProduct showProduct = new ShowProduct();
			// trung bình sao của sản phẩm
			int totalStar = commentService.getAllStarCommentByProductNameSearch(item.getNamesearch());
			showProduct.setProduct(item);
			showProduct.setTotalStar(totalStar);
			listProduct.add(showProduct);
		}

		return listProduct;
	}

	// đếm lượt comment của sản phẩm
	@ModelAttribute("countComment")
	public int countComment(@PathVariable("nameSearch") String nameSearch) {
		int result = commentService.getCountCommentByProductNameSearch(nameSearch);
		return result;
	}

	// trung bình sao của sản phẩm
	@ModelAttribute("totalStar")
	public int totalStar(@PathVariable("nameSearch") String nameSearch) {
		int result = commentService.getAllStarCommentByProductNameSearch(nameSearch);
		return result;
	}
	
	// hiển thị danh sách best seller
	@ModelAttribute("listBestSeller")
	public List<ShowProduct> getListBestSeller(Model model){
		Pageable topFour = PageRequest.of(0, 5);
		
		List<BestSellerModel> list = orderService.getListBestSellerProduct(topFour);
		
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
			List<BestSellerModel> list = favoriteService.getListBestSellerProduct(topFive);

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

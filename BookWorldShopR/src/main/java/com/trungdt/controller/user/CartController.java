package com.trungdt.controller.user;


import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMapping;

import com.trungdt.common.Constants;
import com.trungdt.entity.Discount;
import com.trungdt.model.AlertModel;
import com.trungdt.model.BestSellerModel;
import com.trungdt.model.CartModel;
import com.trungdt.model.ProductModel;
import com.trungdt.model.ShowProduct;
import com.trungdt.service.CommentService;
import com.trungdt.service.DiscountService;
import com.trungdt.service.OrderService;
import com.trungdt.service.ParamService;
import com.trungdt.service.ProductService;
import com.trungdt.service.SessionService;
import com.trungdt.service.impl.ShoppingCartServiceImpl;

@Controller
public class CartController {
	@Autowired
	ShoppingCartServiceImpl cartService;
	
	@Autowired
	DiscountService discountService;
	
	@Autowired
	SessionService sessionService;
	
	@Autowired
	ParamService paramService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	ProductService productService;
	
	// hiện thị giỏ hàng
	@GetMapping("/shop/cart")
	public String index(Model model) {
		model.addAttribute("showDiscount", false);
		// xóa mã giảm giá		
		cartService.clearDiscount();
		model.addAttribute("cart", cartService);
		
		AlertModel alertModel = new AlertModel();
		model.addAttribute("alertModel", alertModel);
		
		return Constants.USER_DISPLAY_SHOPPING_CART;
	}
	
	// update giỏ hàng
	@PostMapping("/cart/update/{id}")
	public String update(@PathVariable("id") Integer id, HttpServletRequest req) {
		// Lấy giá trị số lượng mới từ tham số request
		String qty = req.getParameter("quantity");
		// Lấy thông tin sản phẩm từ service dựa trên ID
		ProductModel product = productService.getOneProductById(id);
		// Kiểm tra xem số lượng mới có nhỏ hơn hoặc bằng số lượng tồn kho không
		if(Integer.parseInt(qty) <= product.getQuality()) {
			// Nếu có, gọi phương thức update từ CartService để cập nhật số lượng sản phẩm trong giỏ hàng
			cartService.update(id, Integer.parseInt(qty));
		}
		
		return "redirect:/shop/cart";
	}
	
	// xóa sản phẩm trong giỏ hàng
	@RequestMapping("/cart/remove/{id}")
	public String remove(@PathVariable("id") Integer id) {
		cartService.remove(id);	
		sessionService.set("sessionProduct", cartService);
		return "redirect:/shop/cart";
	}
	
	// xóa tất cả sản phẩm trong giỏ hàng
	@RequestMapping("/cart/remove")
	public String removeAll() {
		cartService.clearCart();
		sessionService.set("sessionProduct", cartService);
		return "redirect:/shop/cart";
	}
	
	// hiển thị mã giảm giá
	@GetMapping("/shop/cart/discount")
	public String getDiscount() {
		return "redirect:/shop/cart";
	}
	
	
	// áp dụng mã giảm giá
	@PostMapping("/shop/cart/discount")
	public String discount(Model model) {
		// Lấy giá trị mã giảm giá từ tham số "discount" trong request
		String code = paramService.getString("discount", "");
		// Lấy thông tin chi tiết về mã giảm giá từ discountService
		Discount discount = discountService.getDiscountByCode(code);
		// Tạo đối tượng AlertModel để hiển thị thông báo cho người dùng
		AlertModel alertModel = new AlertModel();
		// Kiểm tra xem mã giảm giá có tồn tại hay không
		if(discount == null) {
			// Nếu không tồn tại, xóa mã giảm giá khỏi giỏ hàng và hiển thị thông báo cảnh báo
			cartService.clearDiscount();
			cartService.getAmount();
			alertModel.setAlert("alert-warning");
			alertModel.setContent("Mã giảm giá không tồn tại!");
			alertModel.setDisplay(true);
		}
		
		else {
			// Nếu mã giảm giá tồn tại
			if(cartService.getAmount() >= discount.getMoneylimit()) {
				// Nếu giá trị giỏ hàng đạt hoặc vượt qua giới hạn của mã giảm giá
				// Thêm mã giảm giá vào giỏ hàng và hiển thị thông báo thành công
				cartService.addDiscount(discount.getId(), discount);
				cartService.getAmount();
				alertModel.setAlert("alert-success");
				alertModel.setContent("Bạn đã áp dụng mã giảm giá thành công!");
				alertModel.setDisplay(true);
			}
			else {
				// Nếu giá trị giỏ hàng không đạt đến giới hạn của mã giảm giá
				// Xóa mã giảm giá khỏi giỏ hàng và hiển thị thông báo cảnh báo
				cartService.clearDiscount();
				cartService.getAmount();
				alertModel.setAlert("alert-warning");
				alertModel.setContent("Mã giảm giá không tồn tại!");
				alertModel.setDisplay(true);			
			}
		}
		// Đặt các thông tin vào model để hiển thị trên giao diện
		model.addAttribute("showDiscount", true);
		model.addAttribute("discount", code);
		model.addAttribute("alertModel", alertModel);
		
		model.addAttribute("cart", cartService);
		return Constants.USER_DISPLAY_SHOPPING_CART;
	}
	
	// tổng tiền sản phẩm 
	@ModelAttribute("total")
	public int tolal() {
		// Lấy danh sách các mục trong giỏ hàng
		List<CartModel> list = new ArrayList<>(cartService.getItems());
		int total = 0;
		// Duyệt qua từng mục trong danh sách và tính tổng giá trị
		for(CartModel i: list) {
			total = total + i.getProduct().getPrice() * i.getQuality();
		}
		return total;
	}
	
	

}

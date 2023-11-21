package com.trungdt.controller.user;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.trungdt.common.Constants;
import com.trungdt.entity.Address;
import com.trungdt.entity.Discount;
import com.trungdt.entity.Order;
import com.trungdt.entity.Product;
import com.trungdt.model.CartModel;
import com.trungdt.service.AddressService;
import com.trungdt.service.DiscountService;
import com.trungdt.service.OrderService;
import com.trungdt.service.ParamService;
import com.trungdt.service.ProductService;
import com.trungdt.service.SessionService;
import com.trungdt.service.impl.MailerServiceImpl;
import com.trungdt.service.impl.ShoppingCartServiceImpl;

@Controller
public class CheckOutController {
	@Autowired
	AddressService addressService;

	@Autowired
	AddressService provinceService;
	
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
	ProductService productService;
	
	@Autowired
	MailerServiceImpl mailerService;

	// hiển thị trang checkout
	@GetMapping("/shop/cart/checkout")
	public String index(Model model) {
		// Lấy danh sách các mục trong giỏ hàng
		List<CartModel> listCartModel = new ArrayList<>(cartService.getItems());
		if(listCartModel.isEmpty()) {
			// Nếu giỏ hàng trống, chuyển hướng người dùng đến trang giỏ hàng
			return "redirect:/shop/cart";
		}
		// Nếu giỏ hàng không trống, đặt giỏ hàng vào model để hiển thị trên giao diện
		model.addAttribute("cart", cartService);
		return Constants.USER_DISPLAY_CHECKOUT;
	}
	
	// thanh toán
	@PostMapping("/shop/cart/checkout")
	public String order(Model model) {
		// Lấy thông tin từ request: địa chỉ, phương thức vận chuyển, và comment(ghi chú)
		String addressId = paramService.getString("address_id", "");
		String method = paramService.getString("shipping_method", "");
		String comment = paramService.getString("comment", null);
		// thông tin địa chỉ dựa vào id
		Address address = addressService.getAddressById(Integer.parseInt(addressId));
		
		// Lấy thông tin giảm giá từ giỏ hàng
		Discount discount = cartService.getDiscount();
		// Nếu không có giảm giá, set discount thành null
		if(discount.getId() == 0) {
			discount = null;
		}	
		
		int code;
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = formatter.format(date);
		// Tạo mã đơn hàng duy nhất
		while (true) {
			code = (int) Math.floor(((Math.random() * 899999) + 100000));
			List<Order> list = orderService.getOrderByName(String.valueOf(code));
			if (list.isEmpty()) {
				break;
			}
		}
		// Lấy danh sách các mục trong giỏ hàng
		List<CartModel> listCartModel = new ArrayList<>(cartService.getItems());
		for(CartModel cart: listCartModel) {
			Order order = new Order();
			Product product = cart.getProduct();
			order.setCode(String.valueOf(code));			
			order.setAddress(address);
			order.setDiscount(discount);
			order.setProduct(product);
			order.setQuality(cart.getQuality());
			order.setDate(strDate);
			order.setMethod(method);
			order.setStatus("0");
			order.setComment(comment);
			orderService.save(order);
			// Cập nhật số lượng sản phẩm sau khi đặt hàng
			product.setQuality(product.getQuality() - cart.getQuality());
			// trừ đi số lượng trong kho
			productService.updateQuality(product);
		}
		 // Cập nhật thông tin giảm giá
		discountService.updateQuality(discount);
		// Xóa giỏ hàng và giảm giá sau khi đặt hàng thành công
		cartService.clear();
		cartService.clearDiscount();
		model.addAttribute("cart", cartService);
		// Gửi email xác nhận đặt hàng đến người dùng
		mailerService.queue(address.getUser().getEmail(), "Đặt Hàng Thành Công Tại Web BookWorldR", 
				"Kính chào <b>" + address.getUser().getFullname() +"</b>,<br>"
				+ "Cảm ơn bạn đã mua hàng tại BookWorldR. Mã đơn hàng của bạn là " + code + "<br>"
				+ "Xin vui lòng click vào đường link http://localhost:8080/account/order/invoice/" + code + " để xem chi tiết hóa đơn.<br>"
				+ "<br><br>"
				+ "Xin chân thành cảm ơn đã sử dụng dịch vụ,<br>"
				+ "BookWorldR SHOP");
		
		
		return "redirect:/shop/cart/checkout/success";
	}
	
	// hiển thị trang đặt hàng thành công
	@GetMapping("/shop/cart/checkout/success")
	public String success(Model model) {
		return Constants.USER_DISPLAY_CHECKOUT_SUCCESS;
	}
	
	// hiển thị tổng tiền
	@ModelAttribute("total")
	public int tolal() {
		List<CartModel> list = new ArrayList<>(cartService.getItems());
		int total = 0;
		for(CartModel i: list) {
			total = total + i.getProduct().getPrice() * i.getQuality();
		}
		return total;
	}

	// hiển thị danh sách địa chỉ của người dùng hiện tại
	@ModelAttribute("listAddress")
	public List<Address> getListAddress(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		return addressService.findListAddressByEmail(username);
	}
}

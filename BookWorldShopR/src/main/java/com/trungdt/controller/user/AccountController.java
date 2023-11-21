package com.trungdt.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.trungdt.common.Constants;
import com.trungdt.entity.Favorite;
import com.trungdt.entity.Order;
import com.trungdt.model.AlertModel;
import com.trungdt.model.OrderModel;
import com.trungdt.service.FavoriteService;
import com.trungdt.service.OrderService;
import com.trungdt.service.ParamService;
import com.trungdt.service.UserService;
import com.trungdt.service.impl.ShoppingCartServiceImpl;

@Controller
public class AccountController {
	@Autowired
	UserService userService;

	@Autowired
	FavoriteService favoriteService;

	@Autowired
	OrderService orderService;

	@Autowired
	ShoppingCartServiceImpl cartService;

	@Autowired
	ParamService paramService;

	// hiển thị trang account
	@GetMapping("/account")
	public String index() {
		return Constants.USER_DISPLAY_ACCOUNT_PAGE;
	}

	// hiển thị trang thông tin người dùng
	@GetMapping("/account/information")
	public String information(Model model) {
		return Constants.USER_DISPLAY_ACCOUNT_INFORMATION;
	}

	// hiển thị trang sản phẩm yêu thích của người dùng
	@GetMapping("/account/favorite")
	public String favorite(Model model) {
		// danh sách sản phẩm yêu thích của người dùng hiện tại
		List<Favorite> listFavorite = favoriteService.getListFavoriteByEmail();

		model.addAttribute("listFavorite", listFavorite);
		return Constants.USER_DISPLAY_ACCOUNT_FAVORITE;
	}

	// xóa sản phẩm yêu thích từ người dùng
	@GetMapping("/account/favorite/delete/{id}")
	public String deleteFavorite(@PathVariable("id") int id, Model model) {

		favoriteService.delete(id);

		return "redirect:/account/favorite";
	}

	// hiển thị trang đặt hàng của người dùng
	@GetMapping("/account/order")
	public String order(Model model) {
		// danh sách sản phẩm đã được đặt
		List<OrderModel> listOrderHistory = orderService.listOrderHistory();

		for (OrderModel list : listOrderHistory) {
			// danh sách đã được đặt
			Order order = orderService.getOrderByName(list.getId()).get(0);
			if (order != null) {
				list.setDiscount(order.getDiscount());
			}
		}

		model.addAttribute("listOrder", listOrderHistory);

		return Constants.USER_DISPLAY_ACCOUNT_ORDER;
	}

	// chi tiết đơn hàng
	@GetMapping("/account/order/invoice/{id}")
	public String invoice(@PathVariable("id") String id, Model model) {
		// Lấy danh sách đơn hàng dựa trên mã đơn hàng và tên người dùng
		List<Order> list = orderService.listOrderByCodeAndUsername(id);
		// Kiểm tra xem danh sách đơn hàng có trống hay không
		if (list.isEmpty()) {
			return Constants.USER_DISPLAY_404_PAGE;
		} else {
			// Nếu không trống, tính tổng giá trị và giảm giá (nếu có)
			int total = 0;
			int discount = 0;
			for (Order order : list) {
				total = total + order.getProduct().getPrice() * order.getQuality();
			}
			// Kiểm tra xem có giảm giá không
			if (list.get(0).getDiscount() != null) {
				discount = list.get(0).getDiscount().getPrice();
			}
			model.addAttribute("listProduct", list);
			model.addAttribute("total", total);
			model.addAttribute("discount", discount);
		}
		return Constants.USER_DISPLAY_ACCOUNT_INVOICE;
	}

	// hủy đơn hàng
	@GetMapping("/account/order/cancel/{id}")
	public String cancel(@PathVariable("id") String id, Model model) {
		orderService.cancelOrder(id);
		return "redirect:/account/order";
	}

	// hiển thị trang tìm kiếm đơn hàng
	@GetMapping("/account/order/search")
	public String search(Model model) {
		AlertModel alertModel = new AlertModel();
		model.addAttribute("alertModel", alertModel);
		return Constants.USER_DISPLAY_ACCOUNT_ORDER_SEARCH;
	}

	// tìm kiếm đơn hàng
	@PostMapping("/account/order/search")
	public String searchByCode(Model model) {
		AlertModel alertModel = new AlertModel();
		// Lấy mã đơn hàng từ request parameter
		String code = paramService.getString("code", "");
		// Kiểm tra xem mã đơn hàng có trống không
		if (code.trim().isEmpty()) {
			// Nếu trống, đặt thông báo cảnh báo và hiển thị nó
			alertModel.setAlert("alert-warning");
			alertModel.setContent("Vui lòng nhập mã đơn hàng!");
			alertModel.setDisplay(true);
		} else {
			// Nếu không trống, thực hiện tìm kiếm đơn hàng theo mã
			List<Order> list = orderService.listOrderByCodeAndUsername(code);
			if (list.isEmpty()) {
				// Nếu không tìm thấy đơn hàng, đặt thông báo cảnh báo và hiển thị nó
				alertModel.setAlert("alert-warning");
				alertModel.setContent("Mã đơn hàng không tồn tại!");
				alertModel.setDisplay(true);
			} else {
				// Nếu tìm thấy, chuyển hướng người dùng đến trang chi tiết đơn hàng
				return "redirect:/account/order/invoice/" + code;
			}
		}
		// Đặt mã đơn hàng và đối tượng AlertModel vào model để hiển thị trên giao diện
		model.addAttribute("code", code.trim());
		model.addAttribute("alertModel", alertModel);

		return Constants.USER_DISPLAY_ACCOUNT_ORDER_SEARCH;
	}
}

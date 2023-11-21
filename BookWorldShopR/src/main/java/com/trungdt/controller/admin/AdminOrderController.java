package com.trungdt.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.trungdt.common.Constants;

@Controller
public class AdminOrderController {
	// đơn hàng chờ duyệt
	@GetMapping("/admin/order/list/pending")
	public String pending(Model model) {
		return Constants.USER_DISPLAY_ADMIN_ORDER_PENDING;
	}
	
	// đơn hàng đang giao
	@GetMapping("/admin/order/list/shipping")
	public String shipping(Model model) {
		return Constants.USER_DISPLAY_ADMIN_ORDER_SHIPPING;
	}
	
	// đơn hàng đã giao thành công
	@GetMapping("/admin/order/list/success")
	public String success(Model model) {
		return Constants.USER_DISPLAY_ADMIN_ORDER_SUCCESS;
	}
	
	// đơn hàng hủy
	@GetMapping("/admin/order/list/cancel")
	public String cancel(Model model) {
		return Constants.USER_DISPLAY_ADMIN_ORDER_CANCEL;
	}
}

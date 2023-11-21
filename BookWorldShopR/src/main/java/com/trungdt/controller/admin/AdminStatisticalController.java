package com.trungdt.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.trungdt.common.Constants;

@Controller
public class AdminStatisticalController {
	//Danh Sách Sản Phẩm Bán Chạy Theo Ngày
	@GetMapping("/admin/statistical/product/day")
	public String product(Model model) {
		return Constants.USER_DISPLAY_ADMIN_STATISTICAL_PRODUCT_DAY;
	}
	
	//Danh Sách Sản Phẩm Tồn Kho
	@GetMapping("/admin/statistical/product/warehouse")
	public String warehouse(Model model) {
		return Constants.USER_DISPLAY_ADMIN_STATISTICAL_WAREHOUSE_PRODUCT;
	}
	
	//Thống Kê Doanh Thu
	@GetMapping("/admin/statistical/revenue")
	public String revenue(Model model) {
		return Constants.USER_DISPLAY_ADMIN_STATISTICAL_REVENUE;
	}
	
	//Thống Kê Đơn Hàng
	@GetMapping("/admin/statistical/order")
	public String order(Model model) {
		return Constants.USER_DISPLAY_ADMIN_STATISTICAL_ORDER;
	}
	
}

package com.trungdt.controller.admin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.trungdt.common.Constants;
import com.trungdt.model.StatisticalRevenue;
import com.trungdt.service.OrderService;
import com.trungdt.service.ProductService;
import com.trungdt.service.UserService;

/**
 * Class de hien thi trang chu quan tri
 */
@Controller
public class AdminIndexController {
	@Autowired
	OrderService orderService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ProductService productService;
	
	
	/**
	 * Hien thi trang chu cua giao dien nguoi dung
	 * 
	 * @return trang admin index.html
	 */
	@GetMapping("/admin/index")
	public String index(Model model) {
		// Lấy thời gian hiện tại và chuẩn bị các thành phần của ngày (năm, tháng, ngày)
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	    Date date = new Date();
		String[] today = formatter.format(date).toString().split("-");
	    
		// Thống kê số lượng đơn hàng thành công trong tháng hiện tại
		long order = orderService.getStatisticalTotalOrderOnMonth(Integer.parseInt(today[1]), Integer.parseInt(today[0])).getOrderSuccess();
		
		// Khởi tạo biến doanh thu và lấy danh sách thống kê doanh thu trong tháng
		double revenue = 0;
		List<StatisticalRevenue> listRevenue = orderService.listStatisticalRevenue(Integer.parseInt(today[1]), Integer.parseInt(today[0]));
		
		// Tính tổng doanh thu từ danh sách thống kê
		for(StatisticalRevenue item: listRevenue) {
			revenue = revenue + item.getPrice();
		}
		
		// Thống kê số lượng khách hàng (người dùng không phải là quản trị viên)
		long customer = userService.findAllUserNotRoleAdmin().size();
		
		// Thống kê số lượng sản phẩm
		long product = productService.findAll().size();
		
		// Đặt các giá trị vào model để hiển thị trên giao diện
		model.addAttribute("product", product);
		model.addAttribute("customer", customer);
		model.addAttribute("revenue", revenue);
		model.addAttribute("order", order);
		return Constants.USER_DISPLAY_ADMIN_INDEX;
	}
}

package com.trungdt.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trungdt.entity.Product;
import com.trungdt.model.StatisticalProductDay;
import com.trungdt.model.StatisticalRevenue;
import com.trungdt.model.StatisticalTotalOrder;
import com.trungdt.service.OrderService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest")
public class StatisticalRestController {
	@Autowired
	OrderService orderService;
	
	// danh sách bán chạy trong ngày (admin)
	@GetMapping("/statistical/product/day")
	public List<StatisticalProductDay> productDay(){
		return orderService.listStatisticalProductDay();
	}
	
	// danh sách sản phẩm tồn kho trong chưa bán trong ngày
	@GetMapping("/statistical/product/warehouse")
	public List<Product> warehouse(){
		return orderService.listStatisticalProductWarehouse();
	}
	
	//hiển thị biểu đồ thông kê tháng hiện tại (admin)
	@GetMapping("/statistical/revenue/day/{month}/{year}")
	public List<StatisticalRevenue> listRevenueByDay(@PathVariable("month") int month, @PathVariable("year") int year){
		return orderService.listStatisticalRevenue(month, year);
	}
	
	// hiển thị biểu đổ thông kê doanh thu 12 tháng trong năm (admin)
	@GetMapping("/statistical/revenue/month/{year}")
	public List<StatisticalRevenue> listRevenueByMonth(@PathVariable("year") int year){
		return orderService.listStatisticalRevenueByMonth(year);
	}
	
	// hiển thị biểu đổ thông kê doanh thu của năm (admin)
	@GetMapping("/statistical/revenue/year/{year}")
	public List<StatisticalRevenue> listRevenueByYear(@PathVariable("year") int year){
		return orderService.listStatisticalRevenueByYear(year);
	}
	
	// thông kê đơn hàng trong ngày (admin) 
	@GetMapping("/statistical/order/day/{day}/{month}/{year}")
	public StatisticalTotalOrder listOrderByDay(@PathVariable("day") int day, @PathVariable("month") int month, @PathVariable("year") int year){
		return orderService.getStatisticalTotalOrderOnDay(day, month, year);
	}
	
	// thông kê đơn hàng theo tháng năm (admin)
	@GetMapping("/statistical/order/month/{month}/{year}")
	public StatisticalTotalOrder listOrderByMonth(@PathVariable("month") int month, @PathVariable("year") int year){
		return orderService.getStatisticalTotalOrderOnMonth(month, year);
	}
	
	// thống kê đơn hàng theo năm (admin)
	@GetMapping("/statistical/order/year/{year}")
	public StatisticalTotalOrder listOrderByYear(@PathVariable("year") int year){
		return orderService.getStatisticalTotalOrderOnYear(year);
	}
	
	// hiển thị năm lên modal (admin thống kê)
	@GetMapping("/statisticalOrder/year")
	public List<Integer> listYear(){
		return orderService.getListYearOrder();
	}
	
	// hiển thị thống kê theo lựa chọn (amdin)
	@GetMapping("/statistical/order/option/{day}/{month}/{year}")
	public StatisticalTotalOrder listOrderByOption(@PathVariable("day") int day, @PathVariable("month") int month, @PathVariable("year") int year){
		return orderService.getStatisticalTotalOrderOnOption(day, month, year);
	}
}

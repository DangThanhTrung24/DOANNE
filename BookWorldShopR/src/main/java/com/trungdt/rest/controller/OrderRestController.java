package com.trungdt.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trungdt.model.DetailOrder;
import com.trungdt.model.OrderModel;
import com.trungdt.service.OrderService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/order")
public class OrderRestController {
	@Autowired
	OrderService orderService;

	// hiển thị tất cả đơn hàng chưa duyệt lên trang (admin)
	@GetMapping("/pending")
	public List<OrderModel> getListOrder() {
		return orderService.listOrderGroupByCode();
	}
	
	// hiển thị tất cả đơn hàng đang giao (admin)
	@GetMapping("/shipping")
	public List<OrderModel> getListOrderShipping() {
		return orderService.listOrderGroupByCodeShipping();
	}
	
	// hiển thị danh sách đơn đặt hàng thành công (admin)
	@GetMapping("/success")
	public List<OrderModel> getListOrderSuccess() {
		return orderService.listOrderGroupByCodeSuccess();
	}
	
	// hiển thị đơn hàng đã hủy lên form (admin)
	@GetMapping("/cancel")
	public List<OrderModel> getListOrderCancel() {
		return orderService.listOrderGroupByCodeCancel();
	}
	
	// cập nhật đã giao đơn hàng (admin)
	@PutMapping("/shipped/{id}")
	public void shipped(@PathVariable("id") String id) {
		orderService.shippedOrder(id);
	}
	
	// hiển thị thông tin chi tiết đơn hàng lên modal (admin)
	@GetMapping("/pending/{id}")
	public DetailOrder listOrderByCodeAndUsername(@PathVariable("id") String id) {
		return orderService.getDetailOrderByCode(id);
	}
	
	// duyệt đơn hàng (admin)
	@PutMapping("/approve/{id}")
	public void approve(@PathVariable("id") String id) {
		orderService.approveOrder(id);
	}
	
	// hủy đơn hàng (admin)
	@PutMapping("/cancel/{id}")
	public void cancel(@PathVariable("id") String id) {
		orderService.cancelOrder(id);
	}
	
	// xóa đơn hàng (admin)
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable("id") String id) {
		orderService.deleteOrder(id);
	}
}

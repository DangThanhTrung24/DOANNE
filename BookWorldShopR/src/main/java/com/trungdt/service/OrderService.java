package com.trungdt.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.trungdt.entity.Order;
import com.trungdt.entity.Product;
import com.trungdt.model.BestSellerModel;
import com.trungdt.model.DetailOrder;
import com.trungdt.model.OrderModel;
import com.trungdt.model.StatisticalProductDay;
import com.trungdt.model.StatisticalRevenue;
import com.trungdt.model.StatisticalTotalOrder;

public interface OrderService {

	// danh sách đặt hàng
	List<Order> getOrderByName(String code);

	void save(Order order);

	// danh sách sản phẩm đã được đặt 
	List<OrderModel> listOrderHistory();

	// chi tiết đơn hàng dựa vào id
	List<Order> listOrderByCodeAndUsername(String id);

	// danh sách tất cả đơn hàng chưa duyệt lên trang (admin)
	List<OrderModel> listOrderGroupByCode();

	// thông tin chi tiết đơn hàng  (admin)
	DetailOrder getDetailOrderByCode(String id);

	// duyệt đơn hàng(admin)
	void approveOrder(String id);

	// hủy đơn hàng
	void cancelOrder(String id);

	// danh sách đơn hàng đang giao (admin)
	List<OrderModel> listOrderGroupByCodeShipping();

	// cập nhật đã giao đơn hàng
	void shippedOrder(String id);

	// danh sách đơn đặt hàng thành công (admin)
	List<OrderModel> listOrderGroupByCodeSuccess();

	// hiển thị đơn hàng đã hủy lên form(admin)
	List<OrderModel> listOrderGroupByCodeCancel();

	// xóa đơn hàng (admin)
	void deleteOrder(String id);

	// danh sách bán chạy trong ngày (admin)
	List<StatisticalProductDay> listStatisticalProductDay();
	
	// doanh thu của tất cả đơn hàng đặt thành công trong một tháng (admin)
	List<StatisticalRevenue> listStatisticalRevenue(int month, int year);

	// doanh thu của 12 tháng trong năm (admin)
	List<StatisticalRevenue> listStatisticalRevenueByMonth(int year);

	// doanh thu của năm (admin)
	List<StatisticalRevenue> listStatisticalRevenueByYear(int year);

	// thông kê đơn hàng trong ngày (admin)
	StatisticalTotalOrder getStatisticalTotalOrderOnDay(int day, int month, int year);

	// thông kê số lượng đơn hàng theo trạng thái 
	StatisticalTotalOrder getStatisticalTotalOrderOnMonth(int month, int year);

	// thông skee số lượng đơn hàng theo năm (admin)
	StatisticalTotalOrder getStatisticalTotalOrderOnYear(int year);

	// danh sách năm (admin thống kê)
	List<Integer> getListYearOrder();

	// thống kê theo lựa chọn dựa vào ngày tháng năm (admin thống kê)
	StatisticalTotalOrder getStatisticalTotalOrderOnOption(int day, int month, int year);

	// 5 sản phẩm được bán nhiều nhất
	List<BestSellerModel> getListBestSellerProduct(Pageable topFive);

	// danh sách sản phẩm tồn kho trong ngày (admin)
	List<Product> listStatisticalProductWarehouse();

}

package com.trungdt.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.trungdt.entity.Order;
import com.trungdt.model.BestSellerModel;
import com.trungdt.model.OrderModel;
import com.trungdt.model.StatisticalOrder;
import com.trungdt.model.StatisticalProductDay;

public interface OrderDao extends JpaRepository<Order, Integer>{
	// thông tin đặt hàng được truyền vào
	@Query("SELECT o FROM Order o WHERE o.code = ?1")
	List<Order> getOrderByName(String code);
	
	// lấy các đơn hàng đã được đặt
	@Query("SELECT new OrderModel(o.code, o.address.Fullname, sum(o.quality), sum(o.product.price * o.quality), o.date, o.status) FROM Order o WHERE o.address.user.email = ?1 GROUP BY o.code, o.address.Fullname, o.date, o.status ORDER BY o.date DESC")
	List<OrderModel> listOrderHistory(String email);
	
	// chọn đơn hàng đã hủy câu truy vấn này nhằm lấy thông tin tổng hợp về các đơn hàng có trạng thái là 0(chưa duyệt)
	@Query("SELECT new OrderModel(o.code, o.address.Fullname, sum(o.quality), sum(o.product.price * o.quality), o.date, o.status) FROM Order o WHERE o.status = 0 GROUP BY o.code, o.address.Fullname, o.date, o.status ORDER BY o.date ASC")
	List<OrderModel> listOrderGroupByCodePending();
	
	// chọn đơn hàng đã hủy câu truy vấn này nhằm lấy thông tin tổng hợp về các đơn hàng có trạng thái là 1(đang giao)
	@Query("SELECT new OrderModel(o.code, o.address.Fullname, sum(o.quality), sum(o.product.price * o.quality), o.date, o.status) FROM Order o WHERE o.status = 1 GROUP BY o.code, o.address.Fullname, o.date, o.status ORDER BY o.date ASC")
	List<OrderModel> listOrderGroupByCodeShipping();
	
	// chọn đơn hàng đã hủy câu truy vấn này nhằm lấy thông tin tổng hợp về các đơn hàng có trạng thái là 2(đã giao thành công)
	@Query("SELECT new OrderModel(o.code, o.address.Fullname, sum(o.quality), sum(o.product.price * o.quality), o.date, o.status) FROM Order o WHERE o.status = 2 GROUP BY o.code, o.address.Fullname, o.date, o.status ORDER BY o.date ASC")
	List<OrderModel> listOrderGroupByCodeSuccess();
	
	// chọn đơn hàng đã hủy câu truy vấn này nhằm lấy thông tin tổng hợp về các đơn hàng có trạng thái là 3(đã hủy)
	@Query("SELECT new OrderModel(o.code, o.address.Fullname, sum(o.quality), sum(o.product.price * o.quality), o.date, o.status) FROM Order o WHERE o.status = 3 GROUP BY o.code, o.address.Fullname, o.date, o.status ORDER BY o.date ASC")
	List<OrderModel> listOrderGroupByCodeCancel();
	
	//danh sách các đối tượng Order mà có mã đơn hàng (code) và email của người dùng liên kết với địa chỉ của đơn hàng (userEmail) tương ứng.
	@Query("SELECT o FROM Order o WHERE o.code = ?1 and o.address.user.email = ?2")
	List<Order> listOrderByCodeAndUsername(String code, String username);
	
	// lấy thông tin tổng hợp về các sản phẩm đã được đặt hàng trong một ngày cụ thể (ngày hiện tại),
	@Query("SELECT new StatisticalProductDay(o.product.code, o.product.name, o.product.price, o.product.quality, sum(o.quality)) FROM Order o WHERE CAST(GETDATE() as date) = o.date GROUP BY o.product.code, o.product.name, o.product.price, o.product.quality")
	List<StatisticalProductDay> listStatisticalProductDay();
	
	// lấy ra các đơn hàng đã giao theo tháng và năm được truyền vào (admin thông kê)
	@Query("SELECT new OrderModel(o.code, o.address.Fullname, sum(o.quality), sum(o.product.price * o.quality), o.date, o.status) FROM Order o WHERE (o.status = 2) AND MONTH(o.date) = ?1 AND YEAR(o.date) = ?2 GROUP BY o.code, o.address.Fullname, o.date, o.status ORDER BY o.date DESC")
	List<OrderModel> listStatisticalRevenueMonth(int month, int year);
	
	//lấy ra các đơn hàng đã giao theo năm được truyền vào (admin thông kê)
	@Query("SELECT new OrderModel(o.code, o.address.Fullname, sum(o.quality), sum(o.product.price * o.quality), o.date, o.status) FROM Order o WHERE (o.status = 2) AND YEAR(o.date) = ?1 GROUP BY o.code, o.address.Fullname, o.date, o.status ORDER BY o.date DESC")
	List<OrderModel> listStatisticalRevenueYear(int year);
	
	// lấy ra  các đơn hàng đã giao với ngày tháng năm được truyền vào (admin thong kê)
	@Query("SELECT new OrderModel(o.code, o.address.Fullname, sum(o.quality), sum(o.product.price * o.quality), o.date, o.status) FROM Order o WHERE (o.status = 2) AND DAY(o.date) = ?1 AND MONTH(o.date) = ?2 AND YEAR(o.date) = ?3 GROUP BY o.code, o.address.Fullname, o.date, o.status ORDER BY o.date DESC")
	List<OrderModel> listStatisticalRevenueDay(int day, int month, int year);

	// chọn đơn hàng đã giao với ngày tháng năm được truyền vào (admin thông kê)
	@Query("SELECT new StatisticalOrder(COUNT(o)) FROM Order o WHERE (o.status = 2) AND DAY(o.date) = ?1 AND MONTH(o.date) = ?2 AND YEAR(o.date) = ?3 GROUP BY o.code")
	List<StatisticalOrder> getMaxOrderSuccessOnDay(int day, int month, int year);
	
	// đơn hàng đang giao với ngày tháng năm được truyền vào(admin thống kê)
	@Query("SELECT new StatisticalOrder(COUNT(o)) FROM Order o WHERE (o.status = 1) AND DAY(o.date) = ?1 AND MONTH(o.date) = ?2 AND YEAR(o.date) = ?3 GROUP BY o.code")
	List<StatisticalOrder> getMaxOrderTransportOnDay(int day, int month, int year);
	
	// chọn đơn hàng chưa duyệt với ngày tháng năm đucợ truyền vào (admin thông kê)
	@Query("SELECT new StatisticalOrder(COUNT(o)) FROM Order o WHERE (o.status = 0) AND DAY(o.date) = ?1 AND MONTH(o.date) = ?2 AND YEAR(o.date) = ?3 GROUP BY o.code")
	List<StatisticalOrder> getMaxOrderWaitOnDay(int day, int month, int year);
	
	// đơn hàng đã hủy với ngày tháng năm được truyền vào (admin thông kê)
	@Query("SELECT new StatisticalOrder(COUNT(o)) FROM Order o WHERE (o.status = 3) AND DAY(o.date) = ?1 AND MONTH(o.date) = ?2 AND YEAR(o.date) = ?3 GROUP BY o.code")
	List<StatisticalOrder> getMaxOrderCancelOnDay(int day, int month, int year);
	
	// đếm số luognwj đơn hàng thành công trong một tháng và năm cụ thể nhóm theo mã đơn hàng
	@Query("SELECT new StatisticalOrder(COUNT(o)) FROM Order o WHERE (o.status = 2) AND MONTH(o.date) = ?1 AND YEAR(o.date) = ?2 GROUP BY o.code")
	List<StatisticalOrder> getMaxOrderSuccessOnMonth(int month, int year);
	
	// đếm số luognwj đơn hàng đang giao trong một tháng và năm cụ thể nhóm theo mã đơn hàng
	@Query("SELECT new StatisticalOrder(COUNT(o)) FROM Order o WHERE (o.status = 1) AND MONTH(o.date) = ?1 AND YEAR(o.date) = ?2 GROUP BY o.code")
	List<StatisticalOrder> getMaxOrderTransportOnMonth(int month, int year);
	
	// đếm số lượng đơn hàng được đặt chưa duyệt trong một tháng và năm cụ thể nhóm theo mã đón hàng
	@Query("SELECT new StatisticalOrder(COUNT(o)) FROM Order o WHERE (o.status = 0) AND MONTH(o.date) = ?1 AND YEAR(o.date) = ?2 GROUP BY o.code")
	List<StatisticalOrder> getMaxOrderWaitOnMonth(int month, int year);
	
	//đếm số luognwj đơn hàng hủy trong một tháng và năm cụ thể nhóm theo mã đơn hàng
	@Query("SELECT new StatisticalOrder(COUNT(o)) FROM Order o WHERE (o.status = 3) AND MONTH(o.date) = ?1 AND YEAR(o.date) = ?2 GROUP BY o.code")
	List<StatisticalOrder> getMaxOrderCancelOnMonth(int month, int year);
	
	// đếm số lượng đơn hàng đã giao (admin thông kê) theo năm
	@Query("SELECT new StatisticalOrder(COUNT(o)) FROM Order o WHERE (o.status = 2) AND YEAR(o.date) = ?1 GROUP BY o.code")
	List<StatisticalOrder> getMaxOrderSuccessOnYear(int year);
	
	// đếm số lượng đơn hàng đang giao (admin thông kê) theo năm
	@Query("SELECT new StatisticalOrder(COUNT(o)) FROM Order o WHERE (o.status = 1) AND YEAR(o.date) = ?1 GROUP BY o.code")
	List<StatisticalOrder> getMaxOrderTransportOnYear(int year);
	
	// đếm số lượng đơn hàng chưa duyệt (admin thông kê) theo năm
	@Query("SELECT new StatisticalOrder(COUNT(o)) FROM Order o WHERE (o.status = 0) AND YEAR(o.date) = ?1 GROUP BY o.code")
	List<StatisticalOrder> getMaxOrderWaitOnYear(int year);
	
	// đếm số lượng đơn hàng đã hủy (admin thông kê) theo năm
	@Query("SELECT new StatisticalOrder(COUNT(o)) FROM Order o WHERE (o.status = 3) AND YEAR(o.date) = ?1 GROUP BY o.code")
	List<StatisticalOrder> getMaxOrderCancelOnYear(int year);
	
	// năm gần nhấn có đơn hàng (admin thống kê)
	@Query("SELECT MAX(YEAR(o.date)) FROM Order o")
	int getMaxYearOrder();
	// năm xa nhất có đơn hàng (admin thống kê)
	@Query("SELECT MIN(YEAR(o.date)) FROM Order o")
	int getMinYearOrder();
	
	// khai báo và trả về danh sách bestsellerModel để lưu cột product_id và cột tổng quality từ bảng orders với delete = null và đang active liệt kê theo product(group by o.product) và sx giảm dần
	@Query("SELECT new BestSellerModel(o.product, sum(o.quality)) FROM Order o WHERE o.product.Deleteday = null AND o.product.active = 1 GROUP BY o.product ORDER BY sum(o.quality) DESC")
	List<BestSellerModel> getListBestSellerProduct(Pageable pageable);
}

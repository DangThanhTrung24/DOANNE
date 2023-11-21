package com.trungdt.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.trungdt.dao.DiscountDao;
import com.trungdt.dao.OrderDao;
import com.trungdt.dao.ProductDao;
import com.trungdt.entity.Discount;
import com.trungdt.entity.Order;
import com.trungdt.entity.Product;
import com.trungdt.model.BestSellerModel;
import com.trungdt.model.CartModel;
import com.trungdt.model.DetailOrder;
import com.trungdt.model.OrderModel;
import com.trungdt.model.StatisticalOrder;
import com.trungdt.model.StatisticalProductDay;
import com.trungdt.model.StatisticalRevenue;
import com.trungdt.model.StatisticalTotalOrder;
import com.trungdt.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	OrderDao orderDao;

	@Autowired
	ProductDao productDao;
	
	@Autowired
	DiscountDao discountDao;

	// thông tin đặt hàng được truyền vào
	@Override
	public List<Order> getOrderByName(String code) {
		return orderDao.getOrderByName(code);
	}

	@Override
	public void save(Order order) {
		orderDao.save(order);
	}

	// lịch sử đặt hàng
	@Override
	public List<OrderModel> listOrderHistory() {
		// Lấy thông tin người dùng hiện tại từ SecurityContextHolder
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		// Lấy danh sách đơn hàng từ orderDao dựa vào username
		List<OrderModel> list = orderDao.listOrderHistory(username);

		// Định dạng lại ngày trong danh sách đơn hàng
		for (OrderModel order : list) {
			String[] date = order.getDate().split("-");
			String result = date[2] + "/" + date[1] + "/" + date[0];
			order.setDate(result);
		}

		return list;
	}

	// chi tiết đơn hàng dựa trên id 
	@Override
	public List<Order> listOrderByCodeAndUsername(String id) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		// Gọi phương thức listOrderByCodeAndUsername của orderDao để lấy danh sách đơn hàng
		List<Order> list = orderDao.listOrderByCodeAndUsername(id, username);

		for (Order order : list) {
			String[] date = order.getDate().split("-");
			String result = date[2] + "/" + date[1] + "/" + date[0];
			order.setDate(result);
		}

		return list;
	}

	// danh sách đơn hàng chưa duyệt (admin)
	@Override
	public List<OrderModel> listOrderGroupByCode() {
		// Lấy danh sách các đơn đặt hàng chưa xử lý và đã nhóm theo mã đơn hàng
		List<OrderModel> listOrder = orderDao.listOrderGroupByCodePending();
		
		 // Duyệt qua từng đơn đặt hàng trong danh sách
		for (OrderModel list : listOrder) {
			// Lấy thông tin chi tiết của đơn đặt hàng từ cơ sở dữ liệu dựa trên mã đơn hàng
			Order order = orderDao.getOrderByName(list.getId()).get(0);
			// Kiểm tra xem đơn đặt hàng có tồn tại không
			if (order != null) {
				// Nếu tồn tại, gán giá trị chiết khấu từ đơn đặt hàng vào OrderModel
				list.setDiscount(order.getDiscount());
			}
		}

		return listOrder;
	}

	// thông tin chi tiết đơn hàng (admin)
	@Override
	public DetailOrder getDetailOrderByCode(String id) {
		// Tạo một đối tượng DetailOrder để lưu trữ thông tin chi tiết đơn hàng
		DetailOrder detailOrder = new DetailOrder();
		// Lấy danh sách các đơn đặt hàng dựa trên mã đơn hàng (code)
		List<Order> listOrder = orderDao.getOrderByName(id);

		// Thiết lập các thuộc tính cho đối tượng DetailOrder dựa trên thông tin từ đơn đặt hàng đầu tiên trong danh sách
		detailOrder.setId(listOrder.get(0).getCode());
		detailOrder.setAddress(listOrder.get(0).getAddress().getDetail());
		detailOrder.setComment(listOrder.get(0).getComment());
		detailOrder.setDate(listOrder.get(0).getDate());

		// Xử lý chiết khấu từ đơn đặt hàng
		Discount discount = listOrder.get(0).getDiscount();
		if (discount != null) {
			detailOrder.setDiscount(discount.getPrice());
		} else {
			detailOrder.setDiscount(0);
		}
		
		// Thiết lập các thuộc tính về địa chỉ của người nhận hàng
		detailOrder.setDistrict(listOrder.get(0).getAddress().getDistrict());
		detailOrder.setFullName(listOrder.get(0).getAddress().getUser().getFullname());
		
		// Xác định phương thức thanh toán
		String method = "";
		if (listOrder.get(0).getMethod().equals("1")) {
			method = "Thanh toán online";
		}
		if (listOrder.get(0).getMethod().equals("0")) {
			method = "Thanh toán trực tiếp";
		}
		// Thiết lập các thông tin khác như số điện thoại, tỉnh, quận, phường, ...
		detailOrder.setMethod(method);
		detailOrder.setPhone(listOrder.get(0).getAddress().getPhone());
		detailOrder.setProvince(listOrder.get(0).getAddress().getProvince());
		detailOrder.setWard(listOrder.get(0).getAddress().getWard());
		
		// Tính toán tổng giá trị đơn hàng và các thông tin liên quan
		int subTotal = 0;
		int total = 0;

		 // Tạo danh sách CartModel để lưu trữ thông tin về sản phẩm và số lượng trong đơn hàng
		List<CartModel> listCartModel = new ArrayList<CartModel>();
		// Duyệt qua danh sách các đơn đặt hàng
		for (Order list : listOrder) {
			// Tạo một đối tượng CartModel
			CartModel cartModel = new CartModel();
			// Lấy thông tin về sản phẩm từ đơn đặt hàng
			Product product = new Product();
			product = list.getProduct();
			
			// Thiết lập thông tin cho CartModel
			cartModel.setProduct(product);
			cartModel.setQuality(list.getQuality());
			 // Thêm CartModel vào danh sách
			listCartModel.add(cartModel);

			// Tính tổng giá trị của đơn hàng
			subTotal = subTotal + list.getProduct().getPrice() * list.getQuality();
		}
		
		// Tính tổng giá trị cuối cùng của đơn hàng
		total = subTotal + 30000 - detailOrder.getDiscount();
		
		// Thiết lập các thuộc tính của DetailOrder với các giá trị đã tính toán
		detailOrder.setSubTotal(subTotal);
		detailOrder.setTotal(total);
		detailOrder.setListOrder(listCartModel);

		return detailOrder;
	}

	// duyệt đơn hàng (admin)
	@Override
	public void approveOrder(String id) {
		List<Order> listOrder = orderDao.getOrderByName(id);
		for (Order list : listOrder) {
			list.setStatus("1");
			orderDao.save(list);
		}
	}

	// hủy đơn hàng
	@Override
	public void cancelOrder(String id) {
		// danh sách đơn hàng
		List<Order> listOrder = orderDao.getOrderByName(id);
		for (Order list : listOrder) {
			Product product = list.getProduct();
			product.setQuality(product.getQuality() + list.getQuality());
			list.setStatus("3");
			orderDao.save(list);
			productDao.save(product);
		}
		
		Discount discount = listOrder.get(0).getDiscount();		
		if(discount != null) {
			//nếu đơn hàng có mã giảm giá thì trả lại mã giảm giá
			discount.setQuality(discount.getQuality() + 1);
			discountDao.save(discount);
		}
		
	}

	// danh sách tất cả đơn hàng đang giao (admin)
	@Override
	public List<OrderModel> listOrderGroupByCodeShipping() {
		// Lấy danh sách các đơn đang giao hàng và đã nhóm theo mã đơn hàng
		List<OrderModel> listOrder = orderDao.listOrderGroupByCodeShipping();
		// Duyệt qua từng đơn đặt hàng trong danh sách
		for (OrderModel list : listOrder) {
			// Lấy thông tin chi tiết của đơn đặt hàng từ cơ sở dữ liệu dựa trên mã đơn hàng
			Order order = orderDao.getOrderByName(list.getId()).get(0);
			// Kiểm tra xem đơn đặt hàng có tồn tại không
			if (order != null) {
				 // Nếu tồn tại, gán giá trị chiết khấu từ đơn đặt hàng vào OrderModel
				list.setDiscount(order.getDiscount());
			}
		}

		return listOrder;
	}

	// cập nhật đã giao đơn hang (admin)
	@Override
	public void shippedOrder(String id) {
		// tìm đơn hàng trong danh sách dựa trên id
		List<Order> listOrder = orderDao.getOrderByName(id);
		// Duyệt qua từng đơn đặt hàng trong danh sách
		for (Order list : listOrder) {
			// Cập nhật trạng thái của đơn hàng thành "Đã giao" (status = "2")
			list.setStatus("2");
			orderDao.save(list);
		}
	}

	// đơn đặt hàng thành công (admin)
	@Override
	public List<OrderModel> listOrderGroupByCodeSuccess() {
		// Lấy danh sách các đơn đặt hàng đã được xử lý thành công và đã nhóm theo mã đơn hàng
		List<OrderModel> listOrder = orderDao.listOrderGroupByCodeSuccess();
		// Duyệt qua từng đơn đặt hàng trong danh sách
		for (OrderModel list : listOrder) {
			// Lấy thông tin chi tiết của đơn đặt hàng từ cơ sở dữ liệu dựa trên mã đơn hàng
			Order order = orderDao.getOrderByName(list.getId()).get(0);
			// Kiểm tra xem đơn đặt hàng có tồn tại không
			if (order != null) {
				// Nếu tồn tại, gán giá trị chiết khấu từ đơn đặt hàng vào OrderModel
				list.setDiscount(order.getDiscount());
			}
		}

		return listOrder;
	}

	// hiển thị đơn hàng đã hủy lên form (admni)
	@Override
	public List<OrderModel> listOrderGroupByCodeCancel() {
		// lấy danh sách đơn hàng đã hủy
		List<OrderModel> listOrder = orderDao.listOrderGroupByCodeCancel();

		for (OrderModel list : listOrder) {
			// Lấy đối tượng Order từ cơ sở dữ liệu bằng cách sử dụng ID từ OrderModel
			Order order = orderDao.getOrderByName(list.getId()).get(0);
			// Kiểm tra xem đối tượng Order có tồn tại không
			if (order != null) {
				// Nếu tồn tại, gán giá trị của trường Discount từ đối tượng Order vào OrderModel
				list.setDiscount(order.getDiscount());
			}
		}

		return listOrder;
	}

	// xóa đơn hàng (admin)
	@Override
	public void deleteOrder(String id) {
		// Lấy danh sách các đơn đặt hàng dựa trên mã đơn hàng
		List<Order> listOrder = orderDao.getOrderByName(id);
		// Duyệt qua từng đơn đặt hàng trong danh sách và xóa chúng
		for (Order list : listOrder) {
			orderDao.delete(list);
		}
	}

	// danh sách bán chạy trong ngày (admin)
	@Override
	public List<StatisticalProductDay> listStatisticalProductDay() {
		return orderDao.listStatisticalProductDay();
	}

	// tính doanh thu trong tháng (admin)
	@Override
	public List<StatisticalRevenue> listStatisticalRevenue(int month, int year) {
		// Lấy ra đối tượng Calendar để làm việc với ngày tháng
		Calendar cal = Calendar.getInstance();
		// Đặt tháng của Calendar thành tháng hiện tại
		cal.set(Calendar.MONTH, month - 1);
		// Xác định số ngày tối đa trong tháng
		int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		// Tạo danh sách để lưu trữ thông tin thống kê doanh thu cho mỗi ngày trong tháng
		List<StatisticalRevenue> listRevenue = new ArrayList<StatisticalRevenue>();
		// Lặp qua từng ngày trong tháng
		for (int i = 1; i <= maxDay; i++) {
			long sum = 0; // Khởi tạo biến tổng số tiền
			
			// Lấy danh sách đơn hàng được thống kê vào ngày i trong tháng và năm đã chỉ định
			List<OrderModel> listOrder = new ArrayList<OrderModel>();
			listOrder = orderDao.listStatisticalRevenueDay(i, month, year);

			// Kiểm tra xem danh sách đơn hàng có trống không
			if (!listOrder.isEmpty()) {
				for (OrderModel order : listOrder) {
					// Lấy đối tượng ưu đãi từ đơn hàng
					Discount discount = order.getDiscount();
					// Tính tổng doanh thu từ đơn hàng và cập nhật vào biến sum
					sum = sum + order.getTotal();
					// Nếu có ưu đãi, giảm giá trị ưu đãi khỏi tổng doanh thu
					if (discount != null) {
						sum = sum - discount.getPrice();
					}
					// Thêm 30.000 vào tổng doanh thu (phí ship)
					sum = sum + 30000;
				}

			}

			 // Chuyển đổi tổng doanh thu từ đơn vị đồng sang triệu đồng
			double total = (double) sum / 1000000;
			// làm tròn
			total = Math.round(total * 1000.0) / 1000.0;
			// Tạo đối tượng StatisticalRevenue và thêm vào danh sách
			StatisticalRevenue statistical = new StatisticalRevenue();
			statistical.setPrice(total);
			statistical.setDate(i);
			listRevenue.add(statistical);
		}
		// Trả về danh sách thống kê doanh thu cho từng ngày trong tháng
		return listRevenue;
	}

	// doanh thu từng tháng trong năm (admin)
	@Override
	public List<StatisticalRevenue> listStatisticalRevenueByMonth(int year) {
		// Tạo danh sách để lưu trữ thông tin thống kê doanh thu cho từng tháng
		List<StatisticalRevenue> listRevenue = new ArrayList<StatisticalRevenue>();
		// Lặp qua mỗi tháng trong năm (từ tháng 1 đến tháng 12)
		for (int i = 1; i <= 12; i++) {
			// Khởi tạo biến tổng số tiền doanh thu cho tháng hiện tại
			long sum = 0;
			// Lấy danh sách đơn hàng được thống kê cho tháng và năm đã chỉ định
			List<OrderModel> listOrder = new ArrayList<OrderModel>();
			listOrder = orderDao.listStatisticalRevenueMonth(i, year);

			// Kiểm tra xem danh sách đơn hàng có trống không
			if (!listOrder.isEmpty()) {
				// Lặp qua từng đơn hàng trong danh sách
				for (OrderModel order : listOrder) {
					// Lấy đối tượng ưu đãi từ đơn hàng
					Discount discount = order.getDiscount();
					// Tính tổng doanh thu từ đơn hàng và cập nhật vào biến sum
					sum = sum + order.getTotal();
					// Nếu có ưu đãi, giảm giá trị ưu đãi khỏi tổng doanh thu
					if (discount != null) {
						sum = sum - discount.getPrice();
					}
					// Thêm 30,000 vào tổng doanh thu (phí ship)
					sum = sum + 30000;
				}

			}
			 // Chuyển đổi tổng doanh thu từ đơn vị đồng sang triệu đồng
			double total = (double) sum / 1000000;
			// Tạo đối tượng StatisticalRevenue và thêm vào danh sách
			StatisticalRevenue statistical = new StatisticalRevenue();
			statistical.setPrice(total);
			statistical.setDate(i);
			listRevenue.add(statistical);

		}
		// Trả về danh sách thống kê doanh thu cho từng tháng trong năm
		return listRevenue;
	}

	// doanh thu theo năm(admin)
	@Override
	public List<StatisticalRevenue> listStatisticalRevenueByYear(int year) {
		// Tính năm tối thiểu (3 năm trước năm hiện tại)
		int minYear = year - 3;
		// Tạo danh sách để lưu trữ thông tin thống kê doanh thu cho từng năm
		List<StatisticalRevenue> listRevenue = new ArrayList<StatisticalRevenue>();
		
		// Lặp qua 10 năm, bắt đầu từ năm tối thiểu đến năm hiện tại
		for (int i = 1; i <= 3; i++) {
			// Khởi tạo biến tổng số tiền doanh thu cho năm hiện tại
			long sum = 0;
			
			// Lấy danh sách đơn hàng được thống kê cho năm hiện tại
			List<OrderModel> listOrder = new ArrayList<OrderModel>();
			listOrder = orderDao.listStatisticalRevenueYear(minYear + i);

			// Kiểm tra xem danh sách đơn hàng có trống không
	        if (!listOrder.isEmpty()) {
	            // Lặp qua từng đơn hàng trong danh sách
	            for (OrderModel order : listOrder) {
	                // Lấy đối tượng ưu đãi từ đơn hàng
	                Discount discount = order.getDiscount();

	                // Tính tổng doanh thu từ đơn hàng và cập nhật vào biến sum
	                sum = sum + order.getTotal();

	                // Nếu có ưu đãi, giảm giá trị ưu đãi khỏi tổng doanh thu
	                if (discount != null) {
	                    sum = sum - discount.getPrice();
	                }

	                // Thêm 30,000 vào tổng doanh thu (phí ship)
	                sum = sum + 30000;
	            }
	        }

			double total = (double) sum / 1000000;

			StatisticalRevenue statistical = new StatisticalRevenue();
			statistical.setPrice(total);
			statistical.setDate(minYear + i);
			listRevenue.add(statistical);

		}

		return listRevenue;

	}

	// thông kê đơn hàng theo ngày (amdin)
	@Override
	public StatisticalTotalOrder getStatisticalTotalOrderOnDay(int day, int month, int year) {
		// đơn hàng thành ccong
		List<StatisticalOrder> orderSuccess = orderDao.getMaxOrderSuccessOnDay(day, month, year);
		// đơn hàng chưa duyệt
		List<StatisticalOrder> orderWait = orderDao.getMaxOrderWaitOnDay(day, month, year);
		// đơn hàng đang giao
		List<StatisticalOrder> orderTransport = orderDao.getMaxOrderTransportOnDay(day, month, year);
		// đơn hàng đã hủy
		List<StatisticalOrder> orderCancel = orderDao.getMaxOrderCancelOnDay(day, month, year);

		// đếm số lượng các loại
		int success = orderSuccess.size();
		int wait = orderWait.size();
		int transport = orderTransport.size();
		int cancel = orderCancel.size();

		StatisticalTotalOrder totalOrder = new StatisticalTotalOrder(success, cancel, wait, transport);

		return totalOrder;
	}

	/**
	 * Lấy thông tin thống kê số lượng đơn hàng theo trạng thái trong một tháng cụ thể.
	 *
	 * @param month Tháng cần thống kê.
	 * @param year  Năm cần thống kê.
	 * @return Đối tượng StatisticalTotalOrder chứa thông tin thống kê, bao gồm:
	 *         - Số lượng đơn hàng thành công.
	 *         - Số lượng đơn hàng đang chờ xử lý.
	 *         - Số lượng đơn hàng đang vận chuyển.
	 *         - Số lượng đơn hàng đã hủy.
	 */
	@Override
	public StatisticalTotalOrder getStatisticalTotalOrderOnMonth(int month, int year) {
		// Lấy danh sách các đơn hàng thành công, đang chờ xử lý, đang vận chuyển, và đã hủy trong tháng
		List<StatisticalOrder> orderSuccess = orderDao.getMaxOrderSuccessOnMonth(month, year);
		List<StatisticalOrder> orderWait = orderDao.getMaxOrderWaitOnMonth(month, year);
		List<StatisticalOrder> orderTransport = orderDao.getMaxOrderTransportOnMonth(month, year);
		List<StatisticalOrder> orderCancel = orderDao.getMaxOrderCancelOnMonth(month, year);

		// Lấy số lượng các đơn hàng trong từng trạng thái
		int success = orderSuccess.size();
		int wait = orderWait.size();
		int transport = orderTransport.size();
		int cancel = orderCancel.size();

		// Tạo đối tượng StatisticalTotalOrder chứa thông tin thống kê
		StatisticalTotalOrder totalOrder = new StatisticalTotalOrder(success, cancel, wait, transport);

		return totalOrder;
	}

	// thống kê số luognwj đơn hang theo năm (admin thông kê)
	@Override
	public StatisticalTotalOrder getStatisticalTotalOrderOnYear(int year) {
		List<StatisticalOrder> orderSuccess = orderDao.getMaxOrderSuccessOnYear(year);
		List<StatisticalOrder> orderWait = orderDao.getMaxOrderWaitOnYear(year);
		List<StatisticalOrder> orderTransport = orderDao.getMaxOrderTransportOnYear(year);
		List<StatisticalOrder> orderCancel = orderDao.getMaxOrderCancelOnYear(year);

		int success = orderSuccess.size();
		int wait = orderWait.size();
		int transport = orderTransport.size();
		int cancel = orderCancel.size();

		StatisticalTotalOrder totalOrder = new StatisticalTotalOrder(success, cancel, wait, transport);

		return totalOrder;
	}

	// danh sách năm có nhưng đơn hàng(admin thống kê)
	@Override
	public List<Integer> getListYearOrder() {
		int maxYear = orderDao.getMaxYearOrder();
		int minYear = orderDao.getMinYearOrder();

		List<Integer> listYear = new ArrayList<Integer>();

		for (int i = minYear; i <= maxYear; i++) {
			listYear.add(i);
		}

		return listYear;
	}

	// thống kê đơn hàng theo ngày tháng năm được chọn(admin thống kê)
	@Override
	public StatisticalTotalOrder getStatisticalTotalOrderOnOption(int day, int month, int year) {
		StatisticalTotalOrder totalOrder = new StatisticalTotalOrder();

		// chưa chọn ngày tháng thì lọc theo năm
		if ((day == 0) && (month == 0)) {
			totalOrder = this.getStatisticalTotalOrderOnYear(year);
			// chưa có ngày thì lọc theo tháng năm
		} else if (day == 0) {
			totalOrder = this.getStatisticalTotalOrderOnMonth(month, year);
		} 
		// có thì ngày tháng năm
		else {
			totalOrder = this.getStatisticalTotalOrderOnDay(day, month, year);
		}

		return totalOrder;
	}

	//5 sản phẩm được nhiều người mua nhất
	@Override
	public List<BestSellerModel> getListBestSellerProduct(Pageable topFive) {
		return orderDao.getListBestSellerProduct(topFive);
	}

	// danh sách sản phẩm tồn kho trong ngày (admin)
	@Override
	public List<Product> listStatisticalProductWarehouse() {
		return productDao.listStatisticalProductWarehouse();
	}

}

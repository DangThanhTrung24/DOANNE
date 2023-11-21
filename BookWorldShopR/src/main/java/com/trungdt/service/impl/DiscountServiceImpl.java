package com.trungdt.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.trungdt.dao.DiscountDao;
import com.trungdt.dao.UserDao;
import com.trungdt.entity.Discount;
import com.trungdt.entity.User;
import com.trungdt.model.DiscountModel;
import com.trungdt.service.DiscountService;

@Service
public class DiscountServiceImpl implements DiscountService {
	@Autowired
	DiscountDao discountDao;

	@Autowired
	UserDao userDao;

	// Class cung cap service gui mail
	@Autowired
	MailerServiceImpl mailerService;

	// tạo mã giảm giá
	@Override
	public DiscountModel createDiscount(DiscountModel discountModel) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		User temp = userDao.findUserByEmail(username);

		Discount discount = new Discount();

		discount.setName(discountModel.getName());
		discount.setCode(discountModel.getCode());
		discount.setPrice(discountModel.getPrice());
		discount.setApplyday(discountModel.getApplyDay());
		discount.setExpiration(discountModel.getExpiration());
		discount.setQuality(discountModel.getQuality());
		discount.setMoneylimit(discountModel.getMoneyLimit());

		discount.setPersoncreate(temp.getId());
		discount.setCreateday(timestamp.toString());
		// lưu người tạo ngày tạo và các yếu tố khác vào database
		discountDao.save(discount);
		return discountModel;
	}

	// tất cả mã giảm giá (admin)
	@Override
	public List<Discount> findAll() {
		return discountDao.getListDiscount();
	}

	// hiển thị thông tin mã giảm giá đucợ chọn (admin)
	@Override
	public DiscountModel getOneDiscountById(Integer id) {
		Discount discount = discountDao.findById(id).get();
		DiscountModel discountModel = new DiscountModel();
		discountModel.setName(discount.getName());
		discountModel.setPrice(discount.getPrice());
		discountModel.setCode(discount.getCode());
		discountModel.setApplyDay(discount.getApplyday());
		discountModel.setExpiration(discount.getExpiration());
		discountModel.setMoneyLimit(discount.getMoneylimit());
		discountModel.setQuality(discount.getQuality());
		return discountModel;
	}

	// xóa mã giảm giá (admin)
	@Override
	public void delete(Integer id) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		User temp = userDao.findUserByEmail(username);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		Discount discount = discountDao.findById(id).get();
		discount.setPersondelete(temp.getId());
		discount.setDeleteday(timestamp.toString());
		// lưu người xóa và ... vào db
		discountDao.save(discount);
	}

	// cập nhật mã giảm giá(admin)
	@Override
	public DiscountModel updateDiscount(DiscountModel discountModel) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		User temp = userDao.findUserByEmail(username);

		Discount discount = discountDao.findById(discountModel.getId()).get();
		discount.setName(discountModel.getName());
		discount.setCode(discountModel.getCode());
		discount.setPrice(discountModel.getPrice());
		discount.setApplyday(discountModel.getApplyDay());
		discount.setExpiration(discountModel.getExpiration());
		discount.setQuality(discountModel.getQuality());
		discount.setMoneylimit(discountModel.getMoneyLimit());

		discount.setUpdateday(timestamp.toString());
		discount.setPersonupdate(temp.getId());
		//lưu người cập nhật và các thuộc tính khác
		discountDao.save(discount);
		return discountModel;
	}

	// lấy thông tin mã giảm giá
	@Override
	public Discount getDiscountByCode(String code) {
		return discountDao.getDiscountByCode(code);
	}

	// update số lượng mã giảm giá
	@Override
	public void updateQuality(Discount discount) {
		if (discount != null) {
			// nếu vừa dùng thì - đi 1
			discount.setQuality(discount.getQuality() - 1);
			discountDao.save(discount);
		}
	}

	// hiển thị tất cả mã giảm giá còn hạn (admin)
	@Override
	public List<Discount> getListDiscountAvailable() {
		return discountDao.getListDiscountAvailable();
	}

	// gửi mã giảm giá cho người dùng đã đăng kí (admin)
	@Override
	public User sendCodeDiscount(Integer discountId, User user) {
		// Lấy thông tin chi tiết của mã giảm giá từ ID
		Discount discount = discountDao.findById(discountId).get();
		
		// Chuyển định dạng ngày áp dụng và ngày hết hạn từ yyyy-MM-dd sang dd/MM/yyyy
		String[] applyDay = discount.getApplyday().split("-");
		String resultApplyDay = applyDay[2] + "/" + applyDay[1] + "/" + applyDay[0];
		
		String[] expiration = discount.getExpiration().split("-");
		String resultExpiration = expiration[2] + "/" + expiration[1] + "/" + expiration[0];
		
		// Gửi email thông báo cho người dùng với thông tin chi tiết mã giảm giá
		mailerService.queue(user.getEmail(), "BookWorldR. Thông Tin Khuyến Mãi!", 
				"Xin chào bạn <b>" + user.getFullname() +"</b>,<br>"
				+ "BookWorldR xin thông báo đến bạn chương trình. " + discount.getName() + " khi bạn nhập mã <b>" + discount.getCode() + "</b>." + "<br>"
				+ "Thời gian áp dụng từ ngày " + resultApplyDay +" đến ngày " + resultExpiration + "<br>"
				+ "Số tiền giảm " + discount.getPrice() + "đ<br>"
				+ "Số tiền áp dụng trên " + discount.getMoneylimit() + "đ<br>"
				+ "<br><br>"
				+ "Xin chân thành cảm ơn đã sử dụng dịch vụ,<br>"
				+ "BookWorldR SHOP");
		return user;
	}

}

package com.trungdt.service;

import java.util.List;

import com.trungdt.entity.Discount;
import com.trungdt.entity.User;
import com.trungdt.model.DiscountModel;
import com.trungdt.model.InformationModel;

public interface DiscountService {

	// tạo mã giảm giá (admin)
	DiscountModel createDiscount(DiscountModel discountModel);

	// tất cả mã giảm giá (admin)
	List<Discount> findAll();

	// thông tin mã giảm giá được chọn (admin)
	DiscountModel getOneDiscountById(Integer id);

	// xóa mã giảm giá (admin)
	void delete(Integer id);

	// cập nhật mã giảm giá (admin)
	DiscountModel updateDiscount(DiscountModel discountModel);

	// lấy thông tin mã giảm giá
	Discount getDiscountByCode(String code);

	// cập nhật thông tin mã giảm giá
	void updateQuality(Discount discount);

	// danh sách mã giảm giá còn hạn (admin)
	List<Discount> getListDiscountAvailable();

	// gửi mã cho người dùng đã đăng kí (admin)
	User sendCodeDiscount(Integer discountId, User user);

}

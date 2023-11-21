package com.trungdt.service;

import java.util.Collection;

import com.trungdt.entity.Discount;
import com.trungdt.model.CartModel;

public interface ShoppingCartService {
	// thêm sản phẩm vào giỏ hàng
	void add(Integer id, CartModel entity);
	//xóa sản phẩm dựa trên id
	void remove(Integer id);
	// cập nhật số lượng sản phẩm
	void update(Integer id, int qty);
	void clear();
	Collection<CartModel> getItems();
	int getCount();
	int getCountAllProduct();
	double getAmount();
	void addDiscount(Integer id, Discount entity);
	Discount getDiscount();
	// xóa mã giảm giá
	void clearDiscount();
	// xóa tất cả sản phẩm trong giỏ hàng
	void clearCart();
}

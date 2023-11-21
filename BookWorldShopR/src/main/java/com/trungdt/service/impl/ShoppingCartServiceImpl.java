package com.trungdt.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.trungdt.entity.Discount;
import com.trungdt.model.CartModel;
import com.trungdt.service.ShoppingCartService;

@SessionScope
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

	//lưu thông tin về giỏ hàng sản phẩm với khóa là ID của sản phẩm và giá trị là CartModel
	public static Map<Integer, CartModel> map = new HashMap<>();

	public static Map<Integer, Discount> mapDiscount = new HashMap<>();

	// thêm sản phẩm vào giỏ hàng
	@Override
	public void add(Integer id, CartModel entity) {
		if (map.get(id) != null) {
			this.update(id, entity.getQuality() + 1);
		} else {
			map.put(id, entity);
		}

	}

	// thêm mã giảm giá vào giỏ hàng
	@Override
	public void addDiscount(Integer id, Discount entity) {
		mapDiscount.put(id, entity);
	}

	// tổng tiền
	@Override
	public double getAmount() {
		double amount = 0;
		Set<Integer> set = map.keySet();
		for (Integer i : set) {
			// tổng tiền
			amount += map.get(i).getQuality() * map.get(i).getProduct().getPrice();
		}

		// có mã giảm giá thì được giảm
		if (this.getDiscount() != null) {
			try {
				amount = amount - this.getDiscount().getPrice();
			} catch (Exception e) {

			}
			System.out.println(amount);
		}

		return amount;
	}

	// xóa sản phẩm dựa trên id
	@Override
	public void remove(Integer id) {
		map.remove(id);
	}

	
	// cập nhật số lượng nếu sản phẩm đã có trong giỏ hàng
	@Override
	public void update(Integer id, int qty) {
		map.get(id).setQuality(qty);
	}

	@Override
	public void clear() {
		map.clear();
	}

	// danh sách sản phẩm trong giỏ hàng
	@Override
	public Collection<CartModel> getItems() {
		return map.values();
	}

	@Override
	public int getCount() {
		int count = 0;
		Set<Integer> set = map.keySet();
		for (Integer i : set) {
			count++;
		}
		return count;
	}

	@Override
	public int getCountAllProduct() {
		int count = 0;
		Set<Integer> set = map.keySet();
		for (Integer i : set) {
			count += map.get(i).getQuality();
		}
		return count;
	}

	// thông tin mã giảm giá
	@Override
	public Discount getDiscount() {
		Discount discount = new Discount();
		Set<Integer> set = mapDiscount.keySet();
		for (Integer i : set) {
			discount = mapDiscount.get(i);
		}
		return discount;
	}

	// clear mã giảm giá
	@Override
	public void clearDiscount() {
		mapDiscount.clear();
	}
	
	// xóa tất cả sản phẩm trong giỏ hàng
	@Override
	public void clearCart() {
		map.clear();
	}

}

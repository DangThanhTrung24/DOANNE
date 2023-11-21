package com.trungdt.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trungdt.entity.Discount;
import com.trungdt.entity.User;
import com.trungdt.model.DiscountModel;
import com.trungdt.service.DiscountService;
import com.trungdt.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/discount")
public class DiscountRestController {
	@Autowired
	DiscountService discountService;

	@Autowired
	UserService userService;

	// tạo mã giảm giá (admin)
	@PostMapping("/form")
	public DiscountModel create(@RequestBody DiscountModel discountModel) {
		return discountService.createDiscount(discountModel);
	}

	// hiển thị tất cả mã giảm giá (admin)
	@GetMapping()
	public List<Discount> getAll() {
		return discountService.findAll();
	}

	// thông tin chi tiết của mã giảm giá (admin)
	@GetMapping("/form/{id}")
	public DiscountModel getOneUserById(@PathVariable("id") Integer id) {
		return discountService.getOneDiscountById(id);
	}

	// xóa mã giảm giá (admin)
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		discountService.delete(id);
	}

	// cập nhật mã giảm giá (admin)
	@PutMapping("/form/{id}")
	public DiscountModel update(@PathVariable("id") Integer id, @RequestBody DiscountModel discountModel) {
		return discountService.updateDiscount(discountModel);
	}

	@GetMapping("/apply/{code}")
	public Discount getDiscountByCode(@PathVariable("code") String code) {
		return discountService.getDiscountByCode(code);
	}

	// hiển thị người dùng đăng kí (admin)
	@GetMapping("/user/list")
	List<User> listUser(Model model) {
		return userService.getListUserEnableSubscribe();
	}

	// hiển thị mã giảm giá còn hạn (admin)
	@GetMapping("/available")
	List<Discount> listDiscountAvailable(Model model) {
		return discountService.getListDiscountAvailable();
	}

	// gửi mã giảm giá cho người dùng đã đăng kí (admin)
	@PostMapping("/user/list/{discountId}")
	public User sendCodeDiscount(@PathVariable("discountId") Integer discountId, @RequestBody User user) {
		return discountService.sendCodeDiscount(discountId, user);
	}
}

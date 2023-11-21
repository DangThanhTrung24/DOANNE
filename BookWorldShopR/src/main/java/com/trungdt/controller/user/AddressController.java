package com.trungdt.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.trungdt.common.Constants;
import com.trungdt.entity.Address;
import com.trungdt.service.AddressService;

@Controller
public class AddressController {
	@Autowired
	AddressService addressService;
	
	// hiển thị địa chỉ người dùng
	@GetMapping("/account/address")
	public String index() {
		return Constants.USER_DISPLAY_ACCOUNT_ADDRESS;
	}
	// hiển thị trang thêm địa chỉ
	@GetMapping("/account/address/add")
	public String add(Model model) {
		model.addAttribute("enableBtnUpdate", false);
		return Constants.USER_DISPLAY_ACCOUNT_ADDRESS_ADD;
	}
	
	// xóa địa chỉ
	@GetMapping("/account/address/delete/{id}")
	public String add(@PathVariable("id") int id) {
		Address address = addressService.getAddressById(id);
		
		addressService.delete(address);
		
		return "redirect:/account/address";
	}
	
	// update địa chỉ
	@GetMapping("/account/address/update/{id}")
	public String update(@PathVariable("id") int id, Model model) {
		// lấy user đang đăng nhập
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		// thông tin địa chỉ từ user
		Address address = addressService.findAddressById(username, id);
		
		if(address == null) {
			return "redirect:/account/address";
		}
		
		model.addAttribute("addressId", id);
		model.addAttribute("enableBtnUpdate", true);
		return Constants.USER_DISPLAY_ACCOUNT_ADDRESS_ADD;
	}
	
	// hiển thị địa chỉ của người dùng lên giao diện
	@ModelAttribute("listAddress")
	public List<Address> getListAddress(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		return addressService.findListAddressByEmail(username);
	}
}

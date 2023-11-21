package com.trungdt.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trungdt.entity.InformationShop;
import com.trungdt.model.ShopModel;
import com.trungdt.service.InformationShopService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/shop")
public class InformationShopRestController {
	@Autowired
	InformationShopService informationShopService;
	
	// thêm thông tin mới cho shop (admin)
	@PostMapping("/form")
	public ShopModel create(@RequestBody ShopModel shopModel) {
		return informationShopService.createInformationShop(shopModel);
	}
	
	// hiển thị tất cả các thông tin của từng shop (admin)
	@GetMapping()
	public List<InformationShop> getAll(){
		return informationShopService.findAll();
	}
	
	// xóa thông tin của 1 shop (admin)
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		informationShopService.delete(id);
	}
	
	// cập nhật shop đang hoạt động (admin)
	@PutMapping("/form/active/{id}")
	public ShopModel update(@PathVariable("id") Integer id, @RequestBody ShopModel shopModel) {
		return informationShopService.updateActive(shopModel);
	}
	
	// hiển thị thông tin của shop (admin)
	@GetMapping("/form/{id}")
	public ShopModel getOneShopById(@PathVariable("id") Integer id) {
		return informationShopService.getOneShopById(id);
	}
	
	// cập nhật thông tin chi tiết của shop (admin)
	@PutMapping("/form/{id}")
	public ShopModel updateInformation(@PathVariable("id") Integer id, @RequestBody ShopModel shopModel) {
		return informationShopService.updateInformation(shopModel);
	}
}

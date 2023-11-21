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

import com.trungdt.entity.MenuTwo;
import com.trungdt.model.Nav2Model;
import com.trungdt.service.MenuTwoService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/nav2")
public class Nav2RestController {
	@Autowired
	MenuTwoService menuTwoService;
	
	// thêm một menu 2 (admin)
	@PostMapping("/form")
	public Nav2Model create(@RequestBody Nav2Model nav2Model) {
		return menuTwoService.createNav2(nav2Model);
	}
	
	// hiển thị tất cả menu 2 (admin)
	@GetMapping()
	public List<MenuTwo> getAll(){
		return menuTwoService.findAll();
	}
	
	// xóa danh danh mục menu 2  (admin)
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		menuTwoService.delete(id);
	}
	
	// hiển thị thông tin chi tiết menu 2 (admin)
	@GetMapping("/form/{id}")
	public Nav2Model getOneNav2ById(@PathVariable("id") Integer id) {
		return menuTwoService.getOneNav2ById(id);
	}
	
	// cập nhật thông tin của menu 2 được chọn (admin)
	@PutMapping("/form/{id}")
	public Nav2Model update(@PathVariable("id") Integer id, @RequestBody Nav2Model nav2Model) {
		return menuTwoService.updateNav2(nav2Model);
	}
}

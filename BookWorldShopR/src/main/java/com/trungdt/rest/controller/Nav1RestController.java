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

import com.trungdt.entity.MenuOne;
import com.trungdt.model.Nav1Model;
import com.trungdt.service.MenuOneService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/nav1")
public class Nav1RestController {
	@Autowired
	MenuOneService menuOneService;
	
	// thêm menu bậc 1 cho shop (admin)
	@PostMapping("/form")
	public Nav1Model create(@RequestBody Nav1Model nav1Model) {
		return menuOneService.createNav1(nav1Model);
	}
	
	// hiển thị tất cả các menu 1 lên form (admin)
	@GetMapping()
	public List<MenuOne> getAll(){
		return menuOneService.findAll();
	}
	
	// xóa menu 1 (admin)
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		menuOneService.delete(id);
	}
	
	// hiển thị chi tiết menu 1 (admin)
	@GetMapping("/form/{id}")
	public Nav1Model getOneNav1ById(@PathVariable("id") Integer id) {
		return menuOneService.getOneNav1ById(id);
	}
	
	// cập nhật thông tin menu 1 được chọn (admin)
	@PutMapping("/form/{id}")
	public Nav1Model update(@PathVariable("id") Integer id, @RequestBody Nav1Model nav1Model) {
		return menuOneService.updateNav1(nav1Model);
	}
}

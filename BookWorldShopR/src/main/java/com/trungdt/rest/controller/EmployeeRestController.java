package com.trungdt.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trungdt.entity.UserRole;
import com.trungdt.service.UserRoleService;

/**
 * Class cung cap cac dich vu rest api cho bang employee
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/rest/employees")
public class EmployeeRestController {
	@Autowired
	UserRoleService userRoleService;
	
	// hiển thị tất cả nhân viên lên (admin)
	@GetMapping()
	public List<UserRole> getAll() {
		return userRoleService.findAllAdminOrDirector();
	}
	
	// xóa nhân viên  (admin)
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		userRoleService.delete(id);
	}
}

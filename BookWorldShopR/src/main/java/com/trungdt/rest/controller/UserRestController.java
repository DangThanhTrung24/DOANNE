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

import com.trungdt.entity.User;
import com.trungdt.model.ChangePassModel;
import com.trungdt.model.EmployeeForm;
import com.trungdt.model.InformationModel;
import com.trungdt.service.UserService;

/**
 * Class cung cap cac dich vu rest api cho bang employee
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/rest/user")
public class UserRestController {
	@Autowired
	UserService userService;
	
	// lấy email để kiểm tra (admin)
	@GetMapping("{email}")
	public User getUserByEmail(@PathVariable("email") String email) {
		return userService.findUserByEmail(email);
	}
	
	// lấy tất cả các email (admin)
	@GetMapping()
	public List<User> getAllUser() {
		return userService.findAllUserAnable();
	}
	
	// thông tin account
	@GetMapping("/account")
	public InformationModel getUserAccount() {
		return userService.getUserAccount();
	}
	
	@PostMapping
	public User create(@RequestBody User user) {
		return userService.create(user);
	}
	
	// cập nhật thông tin cá nhân
	@PutMapping("/account/update")
	public InformationModel update(@RequestBody InformationModel informationModel) {
		return userService.update(informationModel);
	}
	
	// cập nhật mật khẩu
	@PutMapping("/account/change-password")
	public ChangePassModel changePass(@RequestBody ChangePassModel changePassModel) {
		return userService.updatePass(changePassModel);
	}
	
	// hiển thị danh sách người dùng (admin)
	@GetMapping("/list")
	public List<User> getAllUserNotRoleAdmin() {
		return userService.findAllUserNotRoleAdmin();
	}
	
	// thêm một người dùng mới (admin)
	@PostMapping("/form")
	public InformationModel createUser(@RequestBody InformationModel informationModel) {
		return userService.createUser(informationModel);
	}
	
	// xóa người dùng (admin)
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		userService.deleteUser(id);
	}
	
	// hiển thị thông tin chi tiết của usr khi được chọn (admin)
	@GetMapping("/update/{id}")
	public InformationModel getOneUserById(@PathVariable("id") Integer id) {
		return userService.getOneUserById(id);
	}
	
	// cập nhật thông tin user được chọn (admin)
	@PutMapping("/form/{id}")
	public InformationModel update(@PathVariable("id") Integer id, @RequestBody InformationModel informationModel) {
		return userService.updateUser(informationModel, id);
	}
}

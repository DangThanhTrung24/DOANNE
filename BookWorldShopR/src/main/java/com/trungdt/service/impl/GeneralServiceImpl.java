package com.trungdt.service.impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.trungdt.dao.EmployeeDao;
import com.trungdt.dao.RoleDao;
import com.trungdt.dao.UserDao;
import com.trungdt.dao.UserRoleDao;
import com.trungdt.entity.Employee;
import com.trungdt.entity.Role;
import com.trungdt.entity.User;
import com.trungdt.entity.UserRole;
import com.trungdt.model.EmployeeForm;
import com.trungdt.service.GeneralService;

import org.springframework.security.core.userdetails.UserDetails;

@Service
public class GeneralServiceImpl implements GeneralService {
	@Autowired
	UserDao userDao;

	@Autowired
	RoleDao roleDao;

	@Autowired
	UserRoleDao userRoleDao;

	@Autowired
	EmployeeDao employeeDao;

	// thêm nhân viên (admin)
	@Override
	public EmployeeForm createEmployee(EmployeeForm employeeForm) {
		// Them user
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		User user = new User();
		user.setEmail(employeeForm.getEmail());
		user.setPassword("123456"); // Mật khẩu mặc định
		user.setFullname(employeeForm.getFullname());
		user.setCreateday(timestamp.toString());
		userDao.save(user);

		// Tim thong tin role theo roleId
		Role role = roleDao.findById(employeeForm.getRole()).get();

		// Them moi mot user co vai tro la ROLE_USER
		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);
		userRoleDao.save(userRole);

		// Them moi mot employee
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		User temp = userDao.findUserByEmail(username);

		Employee employee = new Employee();
		employee.setDepartment(employeeForm.getDepartment());
		employee.setPosition(employeeForm.getPosition());
		employee.setPhone(employeeForm.getPhone());
		employee.setStartday(employeeForm.getStartday());
		employee.setSalary(employeeForm.getSalary());
		employee.setCreateday(timestamp.toString());
		employee.setUser(user);
		employee.setPersoncreate(temp.getId()); // Lưu người tạo nhân viên
		employeeDao.save(employee);

		return employeeForm;
	}

	// hiển thị thông tin nhân viên (admin)
	@Override
	public EmployeeForm getOneUserById(Integer id) {
		// tìm user dựa vào id lấy têm và email
		User user = userDao.findById(id).get();
		EmployeeForm employeeForm = new EmployeeForm();
		employeeForm.setFullname(user.getFullname());
		employeeForm.setEmail(user.getEmail());
		
		// duyệt qua vòng lặp để tìm ra các thuộc tính của nhan viên 
		for (Employee employee : user.getListEmployee()) {
			employeeForm.setDepartment(employee.getDepartment());
			employeeForm.setPhone(employee.getPhone());
			employeeForm.setSalary(employee.getSalary());
			employeeForm.setPosition(employee.getPosition());
			employeeForm.setStartday(employee.getStartday());
		}

		// tìm ra chức vụ của nhân viên 
		for (UserRole userRole : user.getListUserRole()) {
			employeeForm.setRole(userRole.getRole().getId());
		}

		return employeeForm;
	}

	// cập nhật thông tin nhân viên (admin)
	@Override
	public EmployeeForm updateEmployee(EmployeeForm employeeForm) {
		// Cap nhat user
		User user = userDao.findById(employeeForm.getId()).get();
		user.setEmail(employeeForm.getEmail());
		user.setFullname(employeeForm.getFullname());
		userDao.save(user);

		// Cap nhat quyen user
		UserRole userRole = roleDao.getRoleByUserId(employeeForm.getId());
		Role role = roleDao.findById(employeeForm.getRole()).get();
		userRole.setUser(user);
		userRole.setRole(role);
		userRoleDao.save(userRole);
		
		// Cap nhat employee
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		User temp = userDao.findUserByEmail(username);
		
		Employee employee = employeeDao.getEmployeeByUserId(employeeForm.getId());
		employee.setDepartment(employeeForm.getDepartment());
		employee.setPosition(employeeForm.getPosition());
		employee.setPhone(employeeForm.getPhone());
		employee.setStartday(employeeForm.getStartday());
		employee.setSalary(employeeForm.getSalary());
		employee.setUpdateday(timestamp.toString());
		employee.setUser(user);
		employee.setPersonupdate(temp.getId()); // lưu người cập nhật 
		employeeDao.save(employee);
		
		return employeeForm;
	}

}

package com.trungdt.service;

import java.util.List;

import com.trungdt.entity.UserRole;

/**
 * Class cung cap cac dich vu thao tac voi table User_Role trong database
 */
public interface UserRoleService{
	
	/**
	 * Luu user role vao database
	 * 
	 * @param userRole
	 */
	void save(UserRole userRole);

	List<UserRole> findAll();

	// danh sách nhân viên (admin)
	List<UserRole> findAllAdminOrDirector();

	// xóa nhân viên (admin)
	void delete(Integer id);
	
}

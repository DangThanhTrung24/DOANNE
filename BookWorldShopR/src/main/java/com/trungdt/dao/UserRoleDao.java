package com.trungdt.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.trungdt.entity.UserRole;

/**
 * Class thuc hien truy van thong tin bang User_Role trong database
 */
public interface UserRoleDao extends JpaRepository<UserRole, Integer>{
	// hiển thị danh sách các nhân viên 
	@Query("SELECT u FROM UserRole u WHERE (u.role.id = 2 or u.role.id = 3 ) and u.user.Deleteday = null")
	List<UserRole> findAllAdminOrDirector();
}
 
package com.trungdt.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.trungdt.entity.User;

/**
 * Class thuc hien truy van thong tin bang User trong database
 */
public interface UserDao extends JpaRepository<User, Integer> {
	/**
	 * Tim user theo email
	 * 
	 * @param email thong tin email
	 * @return User tim duoc
	 */
	@Query("SELECT u FROM User u WHERE u.email = :uemail and u.Deleteday = null")
	User findUserByEmail(@Param("uemail") String email);

	/**
	 * Tim tat ca cac user co hieu luc
	 * 
	 * @return danh sach User tim duoc
	 */
	@Query("SELECT u FROM User u WHERE u.Deleteday = null")
	List<User> findAllUserAnable();

	// tìm tất cả user khách hàng chưa xóa
	@Query(value = "SELECT * FROM Users WHERE NOT EXISTS(SELECT * FROM Employees WHERE Users.Id = Employees.User_Id) AND DeleteDay is NULL", nativeQuery = true)
	List<User> findAllUserNotRoleAdmin();
	
	/**
	 * Tim tat ca cac user co subscribe hieu luc
	 * 
	 * @return danh sach User tim duoc
	 */
	// chọn các user đã subscribe
	@Query("SELECT u FROM User u WHERE u.Deleteday = null AND u.subscribe = 1")
	List<User> getListUserEnableSubscribe();

}
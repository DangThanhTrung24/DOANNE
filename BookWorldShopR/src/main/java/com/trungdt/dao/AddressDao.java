package com.trungdt.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.trungdt.entity.Address;

public interface AddressDao extends JpaRepository<Address, Integer>{
	// lấy tất cả địa chỉ của người dùng được truyền vào
	@Query("SELECT a FROM Address a WHERE a.user.email = ?1")
	List<Address> findListAddressByEmail(String email);
	
	// chọn địa chỉ của email đang đăng nhập và có id được tuyền vào
	@Query("SELECT a FROM Address a WHERE a.user.email = ?1 and a.id = ?2")
	Address findAddressById(String email, int id);
}

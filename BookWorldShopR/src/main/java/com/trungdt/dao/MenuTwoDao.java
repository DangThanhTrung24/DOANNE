package com.trungdt.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.trungdt.entity.MenuTwo;

public interface MenuTwoDao extends JpaRepository<MenuTwo, Integer>{
	// chọn menu 2 chưa xóa menu 1 chưa xóa và danh mục cũng chưa xóa
	@Query("SELECT m FROM MenuTwo m WHERE m.Deleteday = null and m.menuOne.Deleteday = null and m.menuOne.category.Deleteday = null")
	List<MenuTwo> getListMenuTwo();
}

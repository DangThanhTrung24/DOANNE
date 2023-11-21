package com.trungdt.service;

import java.util.List;

import com.trungdt.entity.MenuTwo;
import com.trungdt.model.Nav2Model;

public interface MenuTwoService {

	// thêm một menu 2 cho loại menu (admin)
	Nav2Model createNav2(Nav2Model nav2Model);

	// hiển thị tất cả menu 2 (admin)
	List<MenuTwo> findAll();

	// xóa danh mục menu 2 (admin)
	void delete(Integer id);

	// hiển thị thông tin chi tiết menu 2(admin)
	Nav2Model getOneNav2ById(Integer id);

	// cập nhật thông tin  menu 2 được chọn  (admin) 
	Nav2Model updateNav2(Nav2Model nav2Model);

}

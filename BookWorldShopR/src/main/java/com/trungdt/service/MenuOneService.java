package com.trungdt.service;

import java.util.List;

import com.trungdt.entity.MenuOne;
import com.trungdt.model.Nav1Model;

public interface MenuOneService {

	// thêm menu bậc 1 cho shop (admin)
	Nav1Model createNav1(Nav1Model nav1Model);

	// hiển thị tất cả menu 1 lên form (admin)
	List<MenuOne> findAll();

	// xóa menu 1 (admin)
	void delete(Integer id);

	// thông tin chi tiết của menu 1 (admin)
	Nav1Model getOneNav1ById(Integer id);

	// cập nahatj thông tin menu1 (admin)
	Nav1Model updateNav1(Nav1Model nav1Model);

}

package com.trungdt.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.trungdt.dao.MenuOneDao;
import com.trungdt.dao.MenuTwoDao;
import com.trungdt.dao.UserDao;
import com.trungdt.entity.MenuOne;
import com.trungdt.entity.MenuTwo;
import com.trungdt.entity.User;
import com.trungdt.model.Nav2Model;
import com.trungdt.service.MenuTwoService;

@Service
public class MenuTwoServiceImpl implements MenuTwoService{
	@Autowired
	UserDao userDao;
	
	@Autowired
	MenuOneDao menuOneDao;
	
	@Autowired
	MenuTwoDao menuTwoDao;

	// thêm một menu 2 cho loại menu (admin)
	@Override
	public Nav2Model createNav2(Nav2Model nav2Model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		User temp = userDao.findUserByEmail(username);
		
		MenuOne menuOne = menuOneDao.findById(nav2Model.getNav1Id()).get();
		
		MenuTwo menuTwo = new MenuTwo();
		
		menuTwo.setName(nav2Model.getName());
		menuTwo.setNamesearch(nav2Model.getNameSearch());
		menuTwo.setMenuOne(menuOne);
		menuTwo.setCreateday(timestamp.toString());
		menuTwo.setPersoncreate(temp.getId());
		
		menuTwoDao.save(menuTwo);
		
		return nav2Model;
	}

	// hiển thị tất cả menu 2 lên form (admin)
	@Override
	public List<MenuTwo> findAll() {
		return menuTwoDao.getListMenuTwo();
	}

	// xóa danh mục menu 2 (admin)
	@Override
	public void delete(Integer id) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		User temp = userDao.findUserByEmail(username);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		MenuTwo menuTwo = menuTwoDao.findById(id).get();
		menuTwo.setDeleteday(timestamp.toString());
		menuTwo.setPersondelete(temp.getId());
		menuTwoDao.save(menuTwo);
	}

	// hiển thị thông tin menu 2 để hiển lên form (admin)
	@Override
	public Nav2Model getOneNav2ById(Integer id) {
		Nav2Model nav2Model = new Nav2Model();
		MenuTwo menuTwo = menuTwoDao.findById(id).get();
		nav2Model.setName(menuTwo.getName());
		nav2Model.setNameSearch(menuTwo.getNamesearch());
		nav2Model.setNav1Id(menuTwo.getMenuOne().getId());
		return nav2Model;
	}

	// cập nhật thong tin menu 2 được chọn (admin)
	@Override
	public Nav2Model updateNav2(Nav2Model nav2Model) {
		MenuOne menuOne = menuOneDao.findById(nav2Model.getNav1Id()).get();
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		User temp = userDao.findUserByEmail(username);
		
		MenuTwo menuTwo = menuTwoDao.findById(nav2Model.getId()).get();
		menuTwo.setName(nav2Model.getName());
		menuTwo.setNamesearch(nav2Model.getNameSearch());
		menuTwo.setMenuOne(menuOne);
		menuTwo.setPersonupdate(temp.getId());
		menuTwo.setUpdateday(timestamp.toString());
		menuTwoDao.save(menuTwo);
		return nav2Model;
	}
}

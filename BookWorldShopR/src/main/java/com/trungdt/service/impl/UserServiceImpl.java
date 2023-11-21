package com.trungdt.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.trungdt.dao.UserDao;
import com.trungdt.entity.User;
import com.trungdt.model.ChangePassModel;
import com.trungdt.model.InformationModel;
import com.trungdt.service.UserService;

/**
 * Class trien khai theo interface UserService, Thao tac voi Class UserDao de
 * thuc hien cac tac vu tuong ung
 */
@Service
public class UserServiceImpl implements UserService {
	// Thong tin class User Dao
	@Autowired
	UserDao userDao;

	/**
	 * Tim user bang email truyen vao
	 * 
	 * @param username thong tin Email
	 * @return User tim duoc
	 */
	@Override
	public User findUserByEmail(String email) {
		// Tra ve User tim duoc
		return userDao.findUserByEmail(email);
	}

	/**
	 * Luu user vao database
	 * 
	 * @param user thong tin user
	 */
	@Override
	public void save(User user) {
		userDao.save(user);
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userDao.findAll();
	}

	@Override
	public User create(User user) {
		return userDao.save(user);
	}

	// danh sách email đã tồn tại (admin)
	@Override
	public List<User> findAllUserAnable() {
		return userDao.findAllUserAnable();
	}

	// lấy thông tin user
	@Override
	public InformationModel getUserAccount() {
		// lấy người dung đang đăng nhập hiện tại
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		// tìm user theo email
		User user = userDao.findUserByEmail(username);
		// tạo đối tuongwj
		InformationModel information = new InformationModel();

		information.setPassword(user.getPassword());
		information.setFullName(user.getFullname());
		information.setEmail(user.getEmail());
		information.setBirthday(user.getBirthday());
		information.setGender(user.getSex());
		information.setNews(user.getSubscribe());

		return information;
	}

	// cập nhật thông tin cá nhân
	@Override
	public InformationModel update(InformationModel informationModel) {
		// lấy người dùng hiện tại
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		// tìm user bagnwf mail
		User user = userDao.findUserByEmail(username);

		user.setFullname(informationModel.getFullName());
		user.setBirthday(informationModel.getBirthday());
		user.setSubscribe(informationModel.getNews());
		user.setSex(informationModel.getGender());
		// lưu vào database
		userDao.save(user);

		return informationModel;
	}

	// cập nhật mật khẩu
	@Override
	public ChangePassModel updatePass(ChangePassModel changePassModel) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		User user = userDao.findUserByEmail(username);

		user.setPassword(changePassModel.getNewPass());

		userDao.save(user);

		return changePassModel;
	}

	// thông tin người dùng dựa trên id
	@Override
	public User findById(Integer id) {
		// TODO Auto-generated method stub
		return userDao.findById(id).get();
	}

	// thêm người dùng mới (admin)
	@Override
	public InformationModel createUser(InformationModel informationModel) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		User user = new User();

		user.setEmail(informationModel.getEmail());
		user.setFullname(informationModel.getFullName());
		user.setBirthday(informationModel.getBirthday());
		user.setSubscribe(informationModel.getNews());
		user.setSex(informationModel.getGender());
		user.setPassword("123456");

		user.setCreateday(timestamp.toString());

		userDao.save(user);

		return informationModel;
	}

	// tìm các user không phải là nhân viên
	@Override
	public List<User> findAllUserNotRoleAdmin() {
		List<User> listUser = userDao.findAllUserNotRoleAdmin();
		return listUser;
	}

	// xóa người udngf (amdin)
	@Override
	public void deleteUser(Integer id) {
		// Xoa user
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		User temp = userDao.findUserByEmail(username);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		User user = userDao.findById(id).get();
		
		user.setDeleteday(timestamp.toString());
		user.setPersondelete(temp.getId());
		userDao.save(user);
	}

	// lấy thôn tin chi tiết của user
	@Override
	public InformationModel getOneUserById(Integer id) {
		User user = userDao.findById(id).get();
		InformationModel information = new InformationModel();
		
		information.setFullName(user.getFullname());
		information.setEmail(user.getEmail());
		information.setGender(user.getSex());
		information.setNews(user.getSubscribe());
		information.setBirthday(user.getBirthday());
		
		//System.out.println("this is username:" + user.getFullname());
		
		return information;
	}

	// cập nhật thông tin user được chọn (admin)
	@Override
	public InformationModel updateUser(InformationModel informationModel, Integer id) {
		
		User user = userDao.findById(id).get();
		
		//user.setEmail(informationModel.getEmail());
		user.setFullname(informationModel.getFullName());
		user.setBirthday(informationModel.getBirthday());
		user.setSubscribe(informationModel.getNews());
		user.setSex(informationModel.getGender());
		
		userDao.save(user);
		
		return informationModel;
	}

	// danh sách người dùng đăng kí (admin)
	@Override
	public List<User> getListUserEnableSubscribe() {
		return userDao.getListUserEnableSubscribe();
	}
}

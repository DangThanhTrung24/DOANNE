package com.trungdt.service;

import java.util.List;

import com.trungdt.entity.User;
import com.trungdt.model.ChangePassModel;
import com.trungdt.model.InformationModel;

/**
 * Class cung cap cac dich vu thao tac voi table Users trong database
 */
public interface UserService {
	
	/**
	 * Tim kiem User bang email
	 * 
	 * @param username thong tin email
	 * @return User tim duoc
	 */
	User findUserByEmail(String email);

	// lưu user
	void save(User user);

	List<User> findAll();

	User create(User user);

	// danh sách email lưu vào localStore (admin)
	List<User> findAllUserAnable();

	// thông tin user
	InformationModel getUserAccount();

	// cập nhật thông tin cá nhân
	InformationModel update(InformationModel informationModel);

	// update mật khẩu
	ChangePassModel updatePass(ChangePassModel changePassModel);

	// thông tin người dùng dựa trên id
	User findById(Integer id);

	// thêm người dùng mới (admin)
	InformationModel createUser(InformationModel informationModel);

	// số lượng user không phải admin
	List<User> findAllUserNotRoleAdmin();

	// xóa nguwof dùng (admin)
	void deleteUser(Integer id);

	// thông tin user được chọn (admin)
	InformationModel getOneUserById(Integer id);

	// cập nhật thông tin user được chọn (admin)
	InformationModel updateUser(InformationModel informationModel, Integer id);

	// danh sách người dùng đăng kí (admin)
	List<User> getListUserEnableSubscribe();

}

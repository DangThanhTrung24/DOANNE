package com.trungdt.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.trungdt.dao.FavoriteDao;
import com.trungdt.dao.ProductDao;
import com.trungdt.dao.UserDao;
import com.trungdt.entity.Favorite;
import com.trungdt.entity.Product;
import com.trungdt.entity.User;
import com.trungdt.model.BestSellerModel;
import com.trungdt.service.FavoriteService;

@Service
public class FavoriteServiceImpl implements FavoriteService {
	@Autowired
	ProductDao productDao;

	@Autowired
	FavoriteDao favoriteDao;

	@Autowired
	UserDao userDao;

	// tạo yêu thích cho sản phẩm
	// @SuppressWarnings("null")
	@Override
	public Favorite create(int id) {
		// Lấy thông tin người dùng hiện tại từ đối tượng SecurityContextHolder.
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		// Tìm người dùng theo địa chỉ email (username) trong cơ sở dữ liệu.
		User temp = userDao.findUserByEmail(username);
		 // Khởi tạo đối tượng Favorite.
		Favorite favorite = new Favorite();

		if (temp != null) {
			// Nếu người dùng được tìm thấy, gọi phương thức getOneFavorite để kiểm tra xem sản phẩm có trong danh sách yêu thích hay không.
			favorite = favoriteDao.getOneFavorite(username, id);

			if (favorite == null) {
				// Nếu sản phẩm chưa có trong danh sách yêu thích, tạo mới một mục yêu thích.
				favorite = new Favorite();
				Date date = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String strDate = formatter.format(date);
				// Lấy thông tin sản phẩm từ cơ sở dữ liệu bằng cách sử dụng id.
				Product product = productDao.findById(id).get();
				// Thiết lập thông tin cho đối tượng Favorite và lưu vào cơ sở dữ liệu.
				favorite.setProduct(product);
				favorite.setUser(temp);
				favorite.setDate(strDate);
				favoriteDao.save(favorite);
			}

			else {
				// Nếu sản phẩm đã có trong danh sách yêu thích, xóa khỏi danh sách.
				favoriteDao.delete(favorite);
				// Đặt giá trị id là 0 để thể hiện rằng mục yêu thích đã bị xóa.
				favorite.setId(0);
			}

		}

		return favorite;
	}

	// sản phẩm yêu thích của từng nguwof dùng đang đăng nhập
	@Override
	public List<Favorite> getListFavoriteByEmail() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		lấy mail người dùng đang đăng nhập
		String username = ((UserDetails) principal).getUsername();
		// sản phẩm yêu thích 
		return favoriteDao.getListFavoriteByEmail(username);
	}

	// bỏ sản phẩm yêu thích
	@Override
	public void delete(int id) {
		// thông tin sản phẩm được yêu thích
		Favorite favorite = favoriteDao.findById(id).get();
		favoriteDao.delete(favorite);
	}

	// sản phẩm đã yêu thích hay chưa khi đăng nhập
	@Override
	public Favorite getOneFavorite(int id) {
		// Khởi tạo đối tượng Favorite
		Favorite favorite = new Favorite();
		// Lấy thông tin người dùng hiện tại từ đối tượng SecurityContextHolder.
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = "";
		try {
			// Lấy tên người dùng nếu người dùng đã đăng nhập (UserDetails).
			username = ((UserDetails) principal).getUsername();
		} catch (Exception e) {
			// Xử lý nếu không thể lấy thông tin người dùng (chưa đăng nhập).
		}
		 // Tìm người dùng theo địa chỉ email (username) trong cơ sở dữ liệu.
		User temp = userDao.findUserByEmail(username);

		if (temp != null) {
			// Nếu người dùng được tìm thấy, gọi phương thức getOneFavorite để lấy thông tin Favorite.
			favorite = favoriteDao.getOneFavorite(username, id);
		} else {
			// Nếu không tìm thấy người dùng, gán giá trị null cho favorite.
			favorite = null;
		}
		return favorite;
	}

	// 5 sản phẩm được yêu thích 
	@Override
	public List<BestSellerModel> getListBestSellerProduct(Pageable topFive) {
		return favoriteDao.getListBestSellerProduct(topFive);
	}

}

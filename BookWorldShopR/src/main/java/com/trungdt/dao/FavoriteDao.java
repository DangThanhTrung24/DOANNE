package com.trungdt.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.trungdt.entity.Favorite;
import com.trungdt.model.BestSellerModel;

public interface FavoriteDao extends JpaRepository<Favorite, Integer>{
	// lấy tất cả sản phẩm được người dùng like
	@Query("SELECT f FROM Favorite f WHERE f.user.email LIKE ?1 and f.product.Deleteday = null")
	List<Favorite> getListFavoriteByEmail(String email);
	
	// xem người dùng hiện tại đã yêu thích sản phẩm đó chưa
	@Query("SELECT f FROM Favorite f WHERE f.user.email LIKE ?1 and f.product.id = ?2")
	Favorite getOneFavorite(String email, int id);
	
	// đếm lượt yêu thích của sản phẩm để hiển thị danh sách sản phẩm được yêu thích nhất
	@Query("SELECT new BestSellerModel(f.product, count(f.product)) FROM Favorite f WHERE f.product.Deleteday = null AND f.product.active = 1 GROUP BY f.product ORDER BY count(f.product) DESC")
	List<BestSellerModel> getListBestSellerProduct(Pageable pageable);
}

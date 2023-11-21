package com.trungdt.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.trungdt.entity.Favorite;
import com.trungdt.model.BestSellerModel;

public interface FavoriteService {

	// tạo yêu thích cho sản phẩm
	Favorite create(int id);

	// danh sách sản phẩm yêu thích của từng người dùng
	List<Favorite> getListFavoriteByEmail();

	// bỏ yêu thích sản phẩm
	void delete(int id);

	// hiển thị yêu thích khi đăng nhập
	Favorite getOneFavorite(int id);

	// danh sách sản phẩm được yêu thích nhất
	List<BestSellerModel> getListBestSellerProduct(Pageable topFive);

}

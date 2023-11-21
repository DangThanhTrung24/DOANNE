package com.trungdt.service;

import java.util.List;

import com.trungdt.entity.InformationShop;
import com.trungdt.model.ShopModel;

public interface InformationShopService {

	// thêm thông tin mới cho shop (admin)
	ShopModel createInformationShop(ShopModel shopModel);

	// danh sách các shop (admin)
	List<InformationShop> findAll();

	// xóa thông tin của shop (admin)
	void delete(Integer id);

	// hiển thị shop đang hoạt đọng (admin)
	ShopModel updateActive(ShopModel shopModel);

	// hiển thị thông tin của shop (admin)
	ShopModel getOneShopById(Integer id);

	// cập nahatj thông tin chi tiết của shop (admin)
	ShopModel updateInformation(ShopModel shopModel);

	InformationShop getOneInformationShop();

}

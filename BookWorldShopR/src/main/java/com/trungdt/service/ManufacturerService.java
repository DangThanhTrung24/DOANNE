package com.trungdt.service;

import java.util.List;

import com.trungdt.entity.Manufacturer;
import com.trungdt.model.ManufacturerModel;

public interface ManufacturerService{

	// thêm nhà cung cấp cho shop (admin)
	ManufacturerModel createManufacturer(ManufacturerModel manufacturerModel);

	// danh sách nhà cung cấp
	List<Manufacturer> findAll();

	// hiển thị thông tin thương hiệu được chọn (admin)
	ManufacturerModel getOneManufacturerById(Integer id);

	// xóa thuongw hiệu (admin)
	void delete(Integer id);

	// cập nhật thương hiệu được chọn (admin)
	ManufacturerModel updateManufacturer(ManufacturerModel manufacturerModel);

}

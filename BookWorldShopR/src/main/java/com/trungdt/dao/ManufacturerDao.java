package com.trungdt.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.trungdt.entity.Manufacturer;

public interface ManufacturerDao extends JpaRepository<Manufacturer, Integer>{
	// danh sách tất cả nhà cung cấp chưa xóa
	@Query("SELECT m FROM Manufacturer m WHERE m.Deleteday = null")
	List<Manufacturer> getListManufacturer();
}

package com.trungdt.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.trungdt.entity.Discount;
public interface DiscountDao extends JpaRepository<Discount, Integer>{
	// chọn tất cả mã giảm giá chưa xóa (admin)
	@Query("SELECT d FROM Discount d WHERE d.Deleteday = null")
	List<Discount> getListDiscount();
	
	// chọn mã giảm giá vẫn dùng được được truyền vào 
	@Query(value="Select * From Discount Where GETDATE() >= ApplyDay and GETDATE() <= Expiration and DeleteDay is null and Code LIKE ?1", nativeQuery = true)
	Discount getDiscountByCode(String code);
	
	// chọn tất cả mã giảm giá còn hạn (admin)
	@Query(value="Select * From Discount Where GETDATE() >= ApplyDay and GETDATE() <= Expiration and DeleteDay is null", nativeQuery = true)
	List<Discount> getListDiscountAvailable();
}

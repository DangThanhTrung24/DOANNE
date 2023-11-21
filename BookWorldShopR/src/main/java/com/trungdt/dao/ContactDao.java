package com.trungdt.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.trungdt.entity.Contact;

public interface ContactDao extends JpaRepository<Contact, Integer> {
//	@Query("SELECT c FROM C c WHERE c.status = 1 AND c.product.id = :uid")
//	List<Comment> getListCommentByProductId(@Param("uid") Integer id);
	
	// đánh giá chưa duyệt (admin)
	@Query("SELECT c FROM Contact c WHERE c.status = 0 ORDER BY c.date DESC")
	List<Contact> getListContactPending();
	
	// lấy các liên hệ đã được duyệt
	@Query("SELECT c FROM Contact c WHERE c.status = 1")
	List<Contact> getListContactChecked();
	
	// hiển thị tất cả thông tin của đánh giá được chọn
	@Query("SELECT c FROM Contact c WHERE c.id = :uid")
	Contact getContactByContactId(@Param("uid") Integer id);
}

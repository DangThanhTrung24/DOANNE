package com.trungdt.service;

import java.util.List;

import com.trungdt.entity.Contact;
import com.trungdt.model.ContactModel;

public interface ContactService {
	// tạo các đánh giá trang contact
	ContactModel createContact(ContactModel contactModel);

	// các đánh giá chưa duyệt (admin)
	List<Contact> getListContactPending();

	// thông tin đánh giá (admin)
	Contact getContactByContactId(Integer id);

	// duyệt đánh giá (admin)
	void approveContact(Integer id);

	// xóa đánh giá (admin)
	void delete(Integer id);

	// danh sách contact đã được duyệt
	List<Contact> getListContactChecked();
	
	List<Contact> findAll();
}

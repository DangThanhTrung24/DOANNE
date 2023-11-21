package com.trungdt.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trungdt.entity.Contact;
import com.trungdt.model.ContactModel;
import com.trungdt.service.ContactService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/contact")
public class ContactRestController {
	@Autowired
	ContactService contactService;
	
	// danh sách contact
	@GetMapping
	public List<Contact> getAll(){
		return contactService.getListContactChecked();
	}
	// post contact
	@PostMapping("/form")
	public ContactModel create(@RequestBody ContactModel contactModel) {
		return contactService.createContact(contactModel);
	}
	
	// hiển thị tất cả đánh giá chưa duyệt (admin)
	@GetMapping("/pending")
	public List<Contact> getListContactPending(){
		return contactService.getListContactPending();
	}
	
	// chi tiết đánh giá (admin)
	@GetMapping("/pending/{id}")
	public Contact getContactByContactId(@PathVariable("id") Integer id) {
		return contactService.getContactByContactId(id);
	}
	
	// duyệt đánh giá (admin)
	@PutMapping("/form/approve/{id}")
	public void approve(@PathVariable("id") Integer id) {
		contactService.approveContact(id);
	}
	
	// xóa đánh giá (admin)
	@DeleteMapping("/form/delete/{id}")
	public void delete(@PathVariable("id") Integer id) {
		contactService.delete(id);
	}
	
	// hiển thị đánh giá đã duyệt (admin0)
	@GetMapping("/approved")
	public List<Contact> getListContactChecked(){
		return contactService.getListContactChecked();
	}
}

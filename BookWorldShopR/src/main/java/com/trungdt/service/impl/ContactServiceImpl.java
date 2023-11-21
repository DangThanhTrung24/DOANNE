package com.trungdt.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trungdt.dao.ContactDao;
import com.trungdt.dao.UserDao;
import com.trungdt.entity.Contact;
import com.trungdt.model.ContactModel;
import com.trungdt.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService {
	@Autowired
	ContactDao dao;
	
	@Autowired
	UserDao userDao;

	@Autowired
	MailerServiceImpl mailerService;
	
	// thêm đánh giá trang contact
	@Override
	public ContactModel createContact(ContactModel contactModel) {
		// Lấy ngày hiện tại và định dạng nó thành chuỗi "yyyy-MM-dd".
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = formatter.format(date);
		// Tạo một đối tượng Contact mới và gán giá trị từ ContactModel vào đó
		Contact contact = new Contact();
		contact.setName(contactModel.getName());
		contact.setEmail(contactModel.getEmail());
		contact.setContent(contactModel.getContent());
		String name = contactModel.getName();
		contact.setDate(strDate);
		contact.setStatus("0");
		// Lưu đối tượng Contact vào cơ sở dữ liệu thông qua đối tượng dao (presumably a DAO or repository).
		dao.save(contact);
//		send mail thanks guests
		mailerService.queue(contactModel.getEmail(), "Thông Báo BookWorldR.com", 
				"Kính chào " + name +",<br>"
				+ "Đại diện BookWorldR shop xin chân thành cảm ơn bạn đã ghé qua và để lại đánh giá ý kiến cá nhân về shop. "
				+ "Ý kiến đóng góp của bạn shop sẽ ghi nhận để góp phần phát triển shop hơn.<br>"
				+ "<br><br>"
				+ "Trân trọng,<br>"
				+ "BookWorldR SHOP");
		// Trả về đối tượng ContactModel được truyền vào (điều này có thể được cải thiện bằng cách trả về đối tượng ContactModel mới tạo sau khi lưu vào cơ sở dữ liệu).
		return contactModel;
	}

	// các đánh giá chưa duyệt(admin)
	@Override
	public List<Contact> getListContactPending() {
		return dao.getListContactPending();
	}

	// chi tiết đánh giá
	@Override
	public Contact getContactByContactId(Integer id) {
		return dao.getContactByContactId(id);
	}

	// duyệt đánh giá 
	@Override
	public void approveContact(Integer id) {
		Contact contact = dao.findById(id).get();
		contact.setStatus("1");
		// set trạng thái và lưu vào db
		dao.save(contact);
	}

	// xóa contact (admin)
	@Override
	public void delete(Integer id) {
		Contact contact = dao.findById(id).get();
		dao.delete(contact);
	}

	// danh sách contact đã được duyệt
	@Override
	public List<Contact> getListContactChecked() {
		return dao.getListContactChecked();
	}

	@Override
	public List<Contact> findAll() {
		return dao.findAll();
	}

	
}

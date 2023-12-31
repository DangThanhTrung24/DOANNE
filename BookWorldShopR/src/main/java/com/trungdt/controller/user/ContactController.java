package com.trungdt.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.trungdt.common.Constants;
import com.trungdt.service.ContactService;
import com.trungdt.service.UserService;

@Controller
public class ContactController {
	@Autowired
	ContactService contactService;
	@Autowired
	UserService userService;
	// hiển thị trang liên hệ
	@GetMapping("/contact")
	public String index(Model model) {
		return Constants.USER_DISPLAY_CONTACT;
	}
}

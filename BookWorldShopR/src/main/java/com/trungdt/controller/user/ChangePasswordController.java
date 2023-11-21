package com.trungdt.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.trungdt.common.Constants;

@Controller
public class ChangePasswordController {
	// hiển thị trang thay đổi mật khẩu
	@GetMapping("/account/change-password")
	public String index() {
		return Constants.USER_DISPLAY_ACCOUNT_CHANGE_PASSWORD;
	}
}

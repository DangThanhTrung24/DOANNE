package com.trungdt.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.trungdt.common.Constants;

@Controller
public class AdminCommentController {
	// danh sách bình luận chưa duyệt (admin)
	@GetMapping("/admin/comment/list/pending")
	public String listPending(Model model) {
		return Constants.USER_DISPLAY_ADMIN_COMMENT_PENDING;
	}
	// trang bình luận đã duyệt (admin)
	@GetMapping("/admin/comment/list/approved")
	public String listApproved(Model model) {
		return Constants.USER_DISPLAY_ADMIN_COMMENT_APPROVED;
	}
}

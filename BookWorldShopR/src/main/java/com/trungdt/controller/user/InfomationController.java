package com.trungdt.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.trungdt.common.Constants;
@Controller
public class InfomationController {
	@GetMapping("/about")
	public String AboutUS() {
		return Constants.USER_DISPLAY_IMFORMATION_ABOUT_US;
	}
	
	@GetMapping("/delivery")
	public String Delivery() {
		return Constants.USER_DISPLAY_IMFORMATION_DELIVERY;
	}
	
	
	@GetMapping("/termCondition")
	public String TermCondition() {
		return Constants.USER_DISPLAY_IMFORMATION_TERM_CONDITION;
	}
}

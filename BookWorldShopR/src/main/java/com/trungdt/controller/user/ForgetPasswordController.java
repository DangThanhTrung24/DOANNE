package com.trungdt.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.trungdt.common.Constants;
import com.trungdt.entity.User;
import com.trungdt.model.UserRegister;
import com.trungdt.service.UserService;
import com.trungdt.service.impl.MailerServiceImpl;

/**
 * Class de lay lai mat khau
 */
@Controller
public class ForgetPasswordController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	MailerServiceImpl mailerService;
	
	@Autowired
	PasswordEncoder pe;

	/**
	 * Hien thi man hinh forget-password
	 * 
	 * @param model
	 * @return man hinh forget-password
	 */
	@GetMapping("/forget-password")
	public String displayFormForgetPassword(Model model) {
		UserRegister userForm = new UserRegister();
		model.addAttribute("userForm", userForm);
		return Constants.USER_DISPLAY_FORGET_PASSWORD;
	}

	// quên mật khẩu
	@PostMapping("/forget-password")
	public String handlerFormForgetPassword(Model model, @ModelAttribute("userForm") @Validated UserRegister userForm,
			BindingResult result) {
		// Kiểm tra xem trường email có trống không
		if (userForm.getEmail().isEmpty()) {
			// Nếu trống, thêm lỗi vào BindingResult để hiển thị trên giao diện
			result.rejectValue("email", "NotBlank.userRegister.email");
		} else {
			// Nếu không trống, kiểm tra xem người dùng có tồn tại không
			User user = userService.findUserByEmail(userForm.getEmail());
			if (user == null) {
				// Nếu không tồn tại, thêm lỗi vào BindingResult để hiển thị trên giao diện
				result.rejectValue("email", "NotExist.userLogin.username");
			}
			else {
				// Nếu tồn tại, mã hóa mật khẩu và gửi email chứa link đặt lại mật khẩu
				String password = pe.encode(user.getPassword());
				mailerService.queue(userForm.getEmail(), "Làm mới mật khẩu!", "Vui lòng click vào link này: "+ "http://localhost:8080/reset-password?code="+password+"&email="+user.getEmail() +" để reset mật khẩu.");
			}
		}
		// Kiểm tra xem có lỗi trong quá trình xử lý không
		if (result.hasErrors()) {
			// Nếu có lỗi, trả về trang đặt lại mật khẩu
			return Constants.USER_DISPLAY_FORGET_PASSWORD;
		}
		// Nếu không có lỗi, đặt thông báo thành công và chuyển hướng đến trang hiển thị thông báo
		model.addAttribute("alert", "Thông báo!");
		model.addAttribute("message", "Vui lòng kiểm tra email để thay đổi mật khẩu!");
		return Constants.USER_DISPLAY_ALERT_STATUS;
	}
}

package com.trungdt.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.trungdt.service.CategoryService;
import com.trungdt.service.InformationShopService;
import com.trungdt.service.ManufacturerService;
import com.trungdt.service.SessionService;
import com.trungdt.service.ShoppingCartService;


@Component
public class GlobalInterceptor implements HandlerInterceptor {
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	InformationShopService informationService;
	
	@Autowired
	SessionService sessionService;
	
	@Autowired
	ShoppingCartService cartService;
	
	@Autowired
	ManufacturerService  manufacturerService;

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		request.setAttribute("categories", categoryService.findAll());
		// hiển thị nhà cung cấp
				request.setAttribute("manufacturer", manufacturerService.findAll());
		request.setAttribute("information", informationService.getOneInformationShop());
		sessionService.set("sessionProduct", cartService);
	}
}

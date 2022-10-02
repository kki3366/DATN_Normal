package com.DATN.Controller.NguoiDung;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class index {

	@RequestMapping("/index")
	public String index() {
		return "nguoiDung/index";
	}
}

package com.DATN.Controller.NguoiDung;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.DATN.configuration.VNPayConfiguration;

@Controller
public class GDThanhCongController {

	@Autowired
	HttpServletRequest req;

	@RequestMapping(value = "paymentSuccess", method = RequestMethod.GET)
	public String a(@RequestParam Map<String, String> getAllParam) {

		if(getAllParam.isEmpty()) {
			return "redirect:/index";
		}else {
			String getBankCode = getAllParam.get("vnp_BankCode");
			Object t = new Object();
			String getName = getAllParam.get("vnp_OrderInfo");
			System.err.println(getName);
			System.err.println(getBankCode);
		}
//		Map<String, String> fields = new HashMap<String, String>();
//		for (Enumeration<?> params = req.getParameterNames(); params.hasMoreElements();) {
//			String fieldName = (String) params.nextElement();
//			String fieldValue = req.getParameter(fieldName);
//			if ((fieldValue != null) && (fieldValue.length() > 0)) {
//				fields.put(fieldName, fieldValue);
//			}
////			System.err.println(fieldName);
////			System.err.println(fieldValue);
//		}
//		
////		if (fields.containsKey("vnp_SecureHash")) {
////			fields.remove("vnp_SecureHash");
////			
////		}
//		String signValue = VNPayConfiguration.hashAllFields(fields);
//		System.err.println(signValue);
		
		return "nguoiDung/GDthanhcong";
	}
}

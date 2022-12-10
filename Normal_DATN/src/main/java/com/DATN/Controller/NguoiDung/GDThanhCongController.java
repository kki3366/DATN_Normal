package com.DATN.Controller.NguoiDung;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpStatusCodeException;

import com.DATN.configuration.VNPayConfiguration;

@Controller
public class GDThanhCongController {

	@Autowired
	HttpServletRequest req;

	@RequestMapping(value = "paymentSuccess", method = RequestMethod.GET)
	public String a(@RequestParam Map<String, String> getAllParam, Model model) throws UnsupportedEncodingException, ParseException {

		if (getAllParam.isEmpty()) {
			return "redirect:/index";
		} else {
			Map<String, String> fields = new HashMap<String, String>();
			for (Enumeration<?> params = req.getParameterNames(); params.hasMoreElements();) {
				String fieldName = URLEncoder.encode((String) params.nextElement(),
						StandardCharsets.US_ASCII.toString());
				String fieldValue = URLEncoder.encode(req.getParameter(fieldName),
						StandardCharsets.US_ASCII.toString());
				if ((fieldValue != null) && (fieldValue.length() > 0)) {
					fields.put(fieldName, fieldValue);
				}
			}
			String vnp_SecureHash = req.getParameter("vnp_SecureHash");
			if (fields.containsKey("vnp_SecureHash")) {
				fields.remove("vnp_SecureHash");
			}
			String signValue = VNPayConfiguration.hashAllFields(fields);
			if (signValue.equals(vnp_SecureHash)) {
				if ("00".equals(req.getParameter("vnp_ResponseCode"))) {
					// Tên ngân hàng - Cần gửi mail
					String bankName = getAllParam.get("vnp_BankCode");
					// Tổng giá tiền - Cần gửi mail
					String amount = getAllParam.get("vnp_Amount");
					// ID người dùng - Cần gửi mail
					String name = getAllParam.get("vnp_OrderInfo").split("\\s+")[0];
					//Mã giao dịch ngân hàng - Cần gửi mail
					String TranNo = getAllParam.get("vnp_BankTranNo");
					//Ngày giao dịch - Cần gửi mail
					String dateTran = getAllParam.get("vnp_PayDate");
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern( "uuuuMMddHHmmss" );
					LocalDateTime ldt = LocalDateTime.parse( dateTran ,dtf);
					//Loại thẻ - Cần gửi mail
					String cardType = getAllParam.get("vnp_CardType");
					
					model.addAttribute("total", Long.parseLong(amount));
					model.addAttribute("account", name);
					model.addAttribute("TranNo", TranNo);
					model.addAttribute("dateTran", ldt);
					
					// Thằng Trung code ở đây cho t
					//Gửi mấy cái t đánh dấu lại cho email người dùng, xong rồi chuyển trang
				}else {
					//Không thành công - Hủy thanh toán
					if("24".equals(req.getParameter("vnp_ResponseCode"))) {
						return "redirect:/index";
					}
					
				}
			} else {
				System.err.println("Không hợp lê");
				return "Error404/index";
			}

		}

		return "nguoiDung/GDthanhcong";
	}
}

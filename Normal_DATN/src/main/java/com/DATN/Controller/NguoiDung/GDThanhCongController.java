package com.DATN.Controller.NguoiDung;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpStatusCodeException;

import com.DATN.Entity.Payment;
import com.DATN.Entity.users;
import com.DATN.Repository.UserRepository;
import com.DATN.Service.PaymentService;
import com.DATN.configuration.VNPayConfiguration;

@Controller
public class GDThanhCongController {

	@Autowired
	HttpServletRequest req;
	
	@Autowired
	PaymentService paymentService;

	@Autowired
	UserRepository user;
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
					
					 boolean checkOrderId = true; // vnp_TxnRef đơn hàng có tồn tại trong database merchant
				        boolean checkAmount = true; // vnp_Amount is valid  (so sánh số tiền VNPAY request và sô tiền của giao dịch trong database merchant)
				        boolean checkOrderStatus = true;
					
					
					// Tên ngân hàng - Cần gửi mail
					String bankName = getAllParam.get("vnp_BankCode");
					// Tổng giá tiền - Cần gửi mail
					String amount = getAllParam.get("vnp_Amount");
					// ID người dùng - Cần gửi mail
					String name = getAllParam.get("vnp_OrderInfo").split("\\s+")[0];
					// Mã hóa đơn
					String orderId = getAllParam.get("vnp_OrderInfo").split("\\s+")[1];
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
					
					Payment payment = new Payment();
					payment.setTxnRef(getAllParam.get("vnp_TxnRef"));
					payment.setAmount(Integer.parseInt(amount));
					payment.setStatusPayment(getAllParam.get("vnp_ResponseCode"));
					payment.setOrderId(Integer.parseInt(orderId));
					payment.setAction("pay");
					paymentService.savePayment(payment);
					// Thằng Trung code ở đây cho t
					//Gửi mấy cái t đánh dấu lại cho email người dùng, xong rồi chuyển trang
					
					users acc = user.getById(req.getRemoteUser());
					
					Properties props = new Properties(); 
					props.setProperty("mail.smtp.auth", "true");
					props.setProperty("mail.smtp.starttls.enable", "true"); 
					props.setProperty("mail.smtp.host", "smtp.gmail.com"); 
					props.setProperty("mail.smtp.ssl.trust","smtp.gmail.com");
					props.setProperty("mail.smtp.ssl.protocols","TLSv1.2");
					props.setProperty("mail.smtp.port", "587");
					
					Session session = Session.getInstance(props, new Authenticator() { 
						protected PasswordAuthentication getPasswordAuthentication() {
					
						String username = "trungttpc01815@fpt.edu.vn";
						String password = "itjpllfnufgbojki";
						return new PasswordAuthentication(username, password);
						}
					});
					
					MimeMessage mime = new MimeMessage(session);
					
					try {
						Multipart mailmultipart = new MimeMultipart();
						
						MimeBodyPart bodytext = new MimeBodyPart();
						DecimalFormat formatter = new DecimalFormat("###,###,###");
						String content= "Kính chào Quý khách,"+
						         "\n\nQuý khách đã thanh toán thành công với"+
								" Loại thẻ "+ cardType +
								", Tên ngân hàng "+bankName+
								
								", Tổng giá tiền "+formatter.format(Integer.parseInt(amount))+" đ"+
								
								", Ngày giao dịch "+ldt+"."+
								
						          "\n Mọi thắc mắc/thông tin phản hồi, Quý khách vui lòng liên hệ qua Hotline:(0292) 7300 468"+
								" hoặc qua email: holywatchshop@gmail.com để được giải đáp."+
								"\n\n Chân thành cảm ơn Quý khách đã mua hàng tại HoLy Watch!"+
								"\n------------------------------------------------------------"+
								"\n Đây là email tự động, Quý khách vui lòng không trả lời email này.";
						
						String subject="HoLy Watch";
						bodytext.setText(content);
						
						
						mailmultipart.addBodyPart(bodytext);
				///
						mime.setFrom(new InternetAddress("holywatchshop@gmail.com"));
						mime.setRecipients(Message.RecipientType.TO,acc.getEmail());
						mime.setSubject(subject,"utf-8");
						mime.setReplyTo(mime.getFrom());
						mime.setText(content,"utf-8");
						
						Transport.send(mime);
						
		
					} catch (AddressException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						
						
					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						
						
					}	
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

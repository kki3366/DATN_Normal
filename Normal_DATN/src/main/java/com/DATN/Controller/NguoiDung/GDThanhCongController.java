package com.DATN.Controller.NguoiDung;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.DATN.Entity.OrderDetail;
import com.DATN.Entity.Orders;
import com.DATN.Entity.Payment;
import com.DATN.Entity.users;
import com.DATN.Repository.OrderDetailRepository;
import com.DATN.Repository.OrdersRepository;
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
	@Autowired
	OrderDetailRepository orderdetai;
	@Autowired
	OrdersRepository order;
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
					Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
					String currentPrincipalName = authentication.getName();
					// Tên ngân hàng - Cần gửi mail
					String bankName = getAllParam.get("vnp_BankCode");
					// Tổng giá tiền - Cần gửi mail
					String amount = getAllParam.get("vnp_Amount");
					// Mã hóa đơn
					String orderId = getAllParam.get("vnp_OrderInfo");
					
					//Mã giao dịch ngân hàng - Cần gửi mail
					String TranNo = getAllParam.get("vnp_BankTranNo");
					//Ngày giao dịch - Cần gửi mail
					String dateTran = getAllParam.get("vnp_PayDate");
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern( "uuuuMMddHHmmss" );
					LocalDateTime ldt = LocalDateTime.parse( dateTran ,dtf);
					//Loại thẻ - Cần gửi mail
					String cardType = getAllParam.get("vnp_CardType");
					
					model.addAttribute("total", Long.parseLong(amount));
					model.addAttribute("account", currentPrincipalName);
					model.addAttribute("TranNo", TranNo);
					model.addAttribute("dateTran", ldt);
					
					Payment payment = new Payment();
					payment.setTxnRef(getAllParam.get("vnp_TxnRef"));
					payment.setAmount(Integer.parseInt(amount));
					payment.setStatusPayment(getAllParam.get("vnp_ResponseCode"));
					payment.setOrderId(Integer.parseInt(orderId));
					payment.setAction("pay");
					paymentService.savePayment(payment);
				
					List<OrderDetail> orderf = (List<OrderDetail>) orderdetai.findByIdOrder(Integer.parseInt(orderId));
					Orders Order = order.getById(Integer.parseInt(orderId));
					
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
					
						String username = "holywatchshop@gmail.com";
						String password = "wppmztfzsqjazrfw";
						return new PasswordAuthentication(username, password);
						}
					});
					
					MimeMessage mime = new MimeMessage(session);
					
					try {
						Multipart mailmultipart = new MimeMultipart("utf-8");
						
						MimeBodyPart bodytext = new MimeBodyPart();
						
						DecimalFormat formatter = new DecimalFormat("###,###,###");
						
						
						String content= "Kính chào Quý khách,"+
						         "\n\nQuý khách đã thanh toán thành công với"+
								" Loại thẻ "+ cardType +
								", Tên ngân hàng "+bankName+
								
								", Tổng giá tiền "+formatter.format(Integer.parseInt(amount)/100)+" đ"+
								
								", Ngày giao dịch "+ldt+"."+
								"Sản phẩm:";
							for (OrderDetail orderDetail : orderf) {
								content+=orderDetail.toString();						
													}
							content+="\n Tổng tiền sản phẩm "+formatter.format(Order.getAmount()-30000)+" đ";
							content+="\n Phí vận chuyển 30.000 đ";
							content+="\n Tổng tiền "+formatter.format(Order.getAmount())+" đ";
							content+="\n Mọi thắc mắc/thông tin phản hồi, Quý khách vui lòng liên hệ qua Hotline:(0292) 7300 468"+
									" hoặc qua email: holywatchshop@gmail.com để được giải đáp."+
									"\n\n Chân thành cảm ơn Quý khách đã mua hàng tại HoLy Watch!"+
									"\n------------------------------------------------------------"+
									"\n Đây là email tự động, Quý khách vui lòng không trả lời email này.";
						String subject="HoLy Watch";
						bodytext.setText(content);
						
						String test = "<table border=1>"
								+ "<tr>"
								+ "<th>Sản Phẩm</th>"
								+ "<th>Số lượng</th>"
								+ "<th>Giá</th>"
								+ "</tr>"
								+ "<tr>"
								+ "<td>Đồng hồ</td>"
								+ "<td>2</td>"
								+ "<td>120.000</td>"
								+ "</tr>"
								+ "</table>";
						mailmultipart.addBodyPart(bodytext);
				///
						mime.setFrom(new InternetAddress("holywatchshop@gmail.com"));
						mime.setRecipients(Message.RecipientType.TO,acc.getEmail());
						mime.setSubject(subject,"utf-8");
						mime.setReplyTo(mime.getFrom());
						mime.setText(content,"utf-8");
						mime.setContent(test,"text/html");
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

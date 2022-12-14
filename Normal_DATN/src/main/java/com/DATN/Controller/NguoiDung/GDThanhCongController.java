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
					// T??n ng??n h??ng - C???n g???i mail
					String bankName = getAllParam.get("vnp_BankCode");
					// T???ng gi?? ti???n - C???n g???i mail
					String amount = getAllParam.get("vnp_Amount");
					// M?? h??a ????n
					String orderId = getAllParam.get("vnp_OrderInfo");
					
					//M?? giao d???ch ng??n h??ng - C???n g???i mail
					String TranNo = getAllParam.get("vnp_BankTranNo");
					//Ng??y giao d???ch - C???n g???i mail
					String dateTran = getAllParam.get("vnp_PayDate");
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern( "uuuuMMddHHmmss" );
					LocalDateTime ldt = LocalDateTime.parse( dateTran ,dtf);
					//Lo???i th??? - C???n g???i mail
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
						
						
						String content= "K??nh ch??o Qu?? kh??ch,"+
						         "\n\nQu?? kh??ch ???? thanh to??n th??nh c??ng v???i"+
								" Lo???i th??? "+ cardType +
								", T??n ng??n h??ng "+bankName+
								
								", T???ng gi?? ti???n "+formatter.format(Integer.parseInt(amount)/100)+" ??"+
								
								", Ng??y giao d???ch "+ldt+".";
						 content += "<table border=1>"
								+ "<tr>"
								+ "<th>S???n Ph???m</th>"
								+ "<th>S??? l?????ng</th>"
								+ "<th>Gi??</th>"
								+ "</tr>";
								
						 content+= "<tr>";
						for (OrderDetail orderDetail : orderf) {
							content+= "<tr>";
							content+=orderDetail.toString();
							content += "</tr>";
							
							}
						content+= "<tr>";
						content+="<td colspan='2'>T???ng ti???n s???n ph???m</td>";
						content+="<td>"+formatter.format(Order.getAmount()-30000)+" ??</td>";
						content += "</tr>";
						content+= "<tr>";
						content+="<td colspan='2'>Ph?? v???n chuy???n</td>";
						content+="<td>30.000 ??</td>";
						content += "</tr>";
						content+= "<tr>";
						content+="<td colspan='2'>T???ng ti???n</td>";
						content+="<td>"+formatter.format(Order.getAmount())+" ??</td>";
						content += "</tr>";
						content += "</table>";	
							
							
							
							content+="<br> M???i th???c m???c/th??ng tin ph???n h???i, Qu?? kh??ch vui l??ng li??n h??? qua Hotline:(0292) 7300 468"+
									" ho???c qua email: holywatchshop@gmail.com ????? ???????c gi???i ????p."+
									"<br><br> Ch??n th??nh c???m ??n Qu?? kh??ch ???? mua h??ng t???i HoLy Watch!"+
									"<br>------------------------------------------------------------"+
									"<br> ????y l?? email t??? ?????ng, Qu?? kh??ch vui l??ng kh??ng tr??? l???i email n??y.";
						String subject="HoLy Watch";
						bodytext.setText(content);
						
						
						mailmultipart.addBodyPart(bodytext);
				///
						mime.setFrom(new InternetAddress("holywatchshop@gmail.com"));
						mime.setRecipients(Message.RecipientType.TO,acc.getEmail());
						mime.setSubject(subject,"utf-8");
						mime.setReplyTo(mime.getFrom());
//						mime.setText(content,"utf-8");
						mime.setContent(content,"text/html; charset=UTF-8");
						Transport.send(mime);
						
		
					} catch (AddressException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						
						
					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						
						
					}	
				}else {
					//Kh??ng th??nh c??ng - H???y thanh to??n
					if("24".equals(req.getParameter("vnp_ResponseCode"))) {
						return "redirect:/index";
					}
					
				}
			} else {
				System.err.println("Kh??ng h???p l??");
				return "Error404/index";
			}

		}

		return "nguoiDung/GDthanhcong";
	}
}

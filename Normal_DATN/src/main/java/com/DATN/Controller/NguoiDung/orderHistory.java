
package com.DATN.Controller.NguoiDung;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;
import org.thymeleaf.exceptions.TemplateInputException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.TimeZone;
import java.util.stream.Collectors;

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
import javax.servlet.http.HttpServletResponse;

import com.DATN.Entity.Cart;
import com.DATN.Entity.OrderDetail;
import com.DATN.Entity.Orders;
import com.DATN.Entity.Payment;
import com.DATN.Entity.Product;
import com.DATN.Entity.users;
import com.DATN.Repository.CartRepository;
import com.DATN.Repository.OrderDetailRepository;
import com.DATN.Repository.OrdersRepository;
import com.DATN.Repository.ProductRepository;
import com.DATN.Repository.UserRepository;
import com.DATN.Service.PaymentService;
import com.DATN.configuration.VNPayConfiguration;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Controller
public class orderHistory {
	@Autowired
	OrdersRepository orderRepository;
	@Autowired
	OrderDetailRepository orderDetailRepository;
	@Autowired
	HttpServletRequest req;
	@Autowired
	CartRepository cartRepository;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	PaymentService paymentService;
	@Autowired
	UserRepository user;
	@Autowired
	HttpServletResponse resp;
	
	
	String publicUrl = null;
	
	@RequestMapping("/orderHistory")
	public String form(Model model, @RequestParam("field") Optional<String> field) {

		if (req.getRemoteUser() != null) {
			List<Cart> ite = cartRepository.findByIdUser(req.getRemoteUser());
			Double tongTien = cartRepository.tongTien(req.getRemoteUser());
			if (tongTien == null) {
				tongTien = (double) 0;
				model.addAttribute("tongTien", tongTien);
			} else {
				model.addAttribute("tongTien", tongTien);
			}
			model.addAttribute("size", ite.size());
		}

		Sort sort = Sort.by(Direction.DESC, field.orElse("orderDate"));

		List<Orders> item = orderRepository.findByIdUser(req.getRemoteUser(), sort);
		model.addAttribute("item", item);
		return "nguoiDung/orderHistory";
	}

	@RequestMapping("/orderHistoryDetail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		if (req.getRemoteUser() != null) {
			List<Cart> ite = cartRepository.findByIdUser(req.getRemoteUser());
			Double tongTien = cartRepository.tongTien(req.getRemoteUser());
			if (tongTien == null) {
				tongTien = (double) 0;
				model.addAttribute("tongTien", tongTien);
			} else {
				model.addAttribute("tongTien", tongTien);
			}
			model.addAttribute("size", ite.size());
		}

		List<OrderDetail> item = orderDetailRepository.findByIdOrder(id);
		model.addAttribute("item", item);
		return "nguoiDung/orderHistoryDetail";
	}

	@RequestMapping("/orderHistory/{id}")
	public String status(Model model, @PathVariable("id") Integer id) throws IOException {
		Orders order = orderRepository.getById(id);
		if (order.getStatus().equals("???? thanh to??n")) {
			
			Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			Payment payment = paymentService.findByOrderId(id);
			VNPayConfiguration config = new VNPayConfiguration();
			
			
			String vnp_TxnRef = payment.getTxnRef();
			String vnp_TransDate = formatter.format(cld.getTime());
			String email = "deathhell1234m@gmail.com";
			int amount = payment.getAmount();
			String trantype = "02";
			String vnp_TmnCode = config.vnp_TmnCode;
			String vnp_IpAddr = config.getIpAddress(req);
			
			
			Map<String, String> vnp_Params = new HashMap<>();
	        vnp_Params.put("vnp_Version", "2.1.0");
	        vnp_Params.put("vnp_Command", "refund");
	        vnp_Params.put("vnp_Amount", String.valueOf(amount));
	        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
	        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
	        vnp_Params.put("vnp_OrderInfo",vnp_TxnRef );
	        vnp_Params.put("vnp_TransDate", vnp_TransDate);
	        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);
	        vnp_Params.put("vnp_CreateBy", email);
	        vnp_Params.put("vnp_TransactionType", trantype);
	        String vnp_CreateDate = formatter.format(cld.getTime());
	        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
	        List fieldNames = new ArrayList(vnp_Params.keySet());
	        Collections.sort(fieldNames);
	        StringBuilder hashData = new StringBuilder();
	        StringBuilder query = new StringBuilder();
	        Iterator itr = fieldNames.iterator();
	        while (itr.hasNext()) {
	            String fieldName = (String) itr.next();
	            String fieldValue = (String) vnp_Params.get(fieldName);
	            if ((fieldValue != null) && (fieldValue.length() > 0)) {
	                //Build hash data
	                hashData.append(fieldName);
	                hashData.append('=');
	                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
	                //Build query
	                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
	                query.append('=');
	                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));

	                if (itr.hasNext()) {
	                    query.append('&');
	                    hashData.append('&');
	                }
	            }
	        }
	        String queryUrl = query.toString();
	        String vnp_SecureHash = config.hmacSHA512(config.vnp_HashSecret , hashData.toString());
	        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
	        String paymentUrl = config.vnp_apiUrl + "?" + queryUrl;
	        System.err.println(paymentUrl);
	        publicUrl = paymentUrl;
	       payment.setAction("refund");
	       paymentService.savePayment(payment);
			order.setStatus("???? h???y");
			orderRepository.save(order);
			
			List<OrderDetail> item = orderDetailRepository.findByIdOrder(order.getId());
			for(OrderDetail od:item) {
				Product product = productRepository.findByNameProduct(od.getName());
				product.setQuantity(product.getQuantity() + od.getQuanlity());
				productRepository.save(product);
			}
			return "redirect:/refundResult/" + queryUrl;

		}
		if (order.getStatus().equals("???? giao")) {

			model.addAttribute("status", true);
		}
		if (order.getStatus().equals("???? ?????t")) {
			order.setStatus("???? h???y");
			orderRepository.save(order);
			//c???ng l???i s??? l?????ng khi h???y
			List<OrderDetail> item = orderDetailRepository.findByIdOrder(order.getId());
			for(OrderDetail od:item) {
				Product product = productRepository.findByNameProduct(od.getName());
				product.setQuantity(product.getQuantity() + od.getQuanlity());
				productRepository.save(product);
			}
		}
		return "redirect:/orderHistory";
	}
	
	@RequestMapping(value = "/refundResult/{t}")
	public String t(@PathVariable String t, Model model) throws IOException {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String currentPrincipalName = authentication.getName();
			VNPayConfiguration config = new VNPayConfiguration();
			String paymentUrl = config.vnp_apiUrl + "?" + t;
			URL url = new URL(paymentUrl);
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setRequestMethod("GET");
	        BufferedReader in = new BufferedReader(
	                new InputStreamReader(connection.getInputStream()));
	        String inputLine;
	        StringBuilder response = new StringBuilder();
	        while ((inputLine = in.readLine()) != null) {
	            response.append(inputLine);
	        }
	        in.close();
	        String Rsp = response.toString();
	        String respDecode = URLDecoder.decode(Rsp, "UTF-8");
	        MultiValueMap<String, String> query = UriComponentsBuilder.fromUriString(config.vnp_apiUrl + "?" +respDecode).build().getQueryParams();
	        if("[97]".equals(query.get("vnp_ResponseCode").toString())) {
	        	System.err.println("sai chu ki");
	        }else {
	        	String amounRefund = query.get("vnp_Amount").toString().replace("[", "").replace("]", "");
	        	String Ref = query.get("vnp_TxnRef").toString().replace("[", "").replace("]", "");
	        	String dateTran = query.get("vnp_PayDate").toString().replace("[", "").replace("]", "");
	        	DateTimeFormatter dtf = DateTimeFormatter.ofPattern( "uuuuMMddHHmmss" );
				LocalDateTime ldt = LocalDateTime.parse( dateTran ,dtf);
	            model.addAttribute("amounRefund", Integer.parseInt(amounRefund));
	            model.addAttribute("username", currentPrincipalName);
	            model.addAttribute("Ref", Ref);
	            model.addAttribute("dateTran", ldt);
	            
	            //Mail ho??n ti???n
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
					Multipart mailmultipart = new MimeMultipart();
					
					MimeBodyPart bodytext = new MimeBodyPart();
					DecimalFormat formatter = new DecimalFormat("###,###,###");
					String content= "K??nh ch??o Qu?? kh??ch,"+
					         "\n\n????n h??ng c???a Qu?? kh??ch ???? h???y th??nh c??ng"+
					         "\nT??i li??n h??? v??? kho???n ti???n ho??n l???i m?? b???n ???? th???c hi???n v??o ng??y "+ldt+"."+

					         "\n\nKho???n ti???n ho??n l???i c???a b???n ???? ???????c g???i v??o t??i kho???n c???a b???n v???i s??? ti???n "+formatter.format(Integer.parseInt(amounRefund)/100)+" ??"+"."+ 
					         "\nTh??ng th?????ng, ng??n h??ng nh???n ti???n s??? m???t t??? ??????3 ?????n 5 ng??y l??m vi???c ????? ghi c?? ti???n v??o t??i kho???n c???a b???n."+

					         "\n\nN???u b???n kh??ng th???y ti???n ho??n l???i trong t??i kho???n c???a m??nh,"+
					         "h??y tr??? l???i email n??y v?? ch??ng t??i s??? xem x??t ngay l???p t???c."+
					         "\nTrong th???i gian ch??? ?????i, vui l??ng cho t??i bi???t n???u b???n c?? th??m b???t k??? c??u h???i ho???c th???c m???c n??o ??? "+
					          "t??i r???t s???n l??ng tr??? gi??p!"+
					         "\n\nC???m ??n,";
							
					         
					
					String subject="HoLy Watch";
					bodytext.setText(content);
					
					
					mailmultipart.addBodyPart(bodytext);
			
					mime.setFrom(new InternetAddress("holywatchshop@gmail.com"));
					mime.setRecipients(Message.RecipientType.TO,acc.getEmail());
					mime.setSubject(subject,"utf-8");
					mime.setReplyTo(mime.getFrom());
					mime.setText(content,"utf-8");
					
					Transport.send(mime);
					
	//
				} catch (AddressException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
					
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
					
				}	
	            
	            
	           
	        }
			return "nguoiDung/Refundthanhcong";
		} catch (NullPointerException e) {
			return "redirect:/index";
		}

	
	}
}

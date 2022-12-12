
package com.DATN.Controller.NguoiDung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AcceptAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.DATN.Entity.Cart;
import com.DATN.Entity.Category;
import com.DATN.Entity.OrderDetail;
import com.DATN.Entity.Orders;
import com.DATN.Entity.Product;
import com.DATN.Entity.users;
import com.DATN.Repository.CartRepository;
import com.DATN.Repository.CategoryRepository;
import com.DATN.Repository.OrderDetailRepository;
import com.DATN.Repository.OrdersRepository;
import com.DATN.Repository.ProductRepository;
import com.DATN.Repository.UserRepository;
import com.DATN.Service.CartService;
import com.DATN.Service.ProductService;
import com.DATN.Unit.FileUploadUtil;
import com.DATN.configuration.VNPayConfiguration;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Controller
@Validated
public class checkout {
	@Autowired
	CartRepository cartRepository;
	@Autowired
	CartService cartService;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	OrdersRepository ordersRepository;
	@Autowired
	OrderDetailRepository orderDetailRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	HttpServletRequest req;
	
	@Autowired
	HttpServletResponse resp;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	ServletContext app;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@RequestMapping("/checkout")
	public String form(Model model) {
		List<Cart> item = cartRepository.findByIdUser(req.getRemoteUser());
	
		Double tongTien = cartRepository.tongTien(req.getRemoteUser());
		users acc = userRepository.getById(req.getRemoteUser());
		
		
		model.addAttribute("item", item);
		model.addAttribute("size", item.size());
		if(tongTien == null) {
			tongTien = (double) 0;
			model.addAttribute("tongTien", tongTien);
		}else {
			model.addAttribute("tongTien", tongTien);
		}
		model.addAttribute("acc", acc);
		model.addAttribute("order", new Orders());

		return "nguoiDung/checkout";
		
	}
	
	@RequestMapping(value = "/addOrder")
	public String add( Orders order ,Model model) throws IOException {
		String vnpayUrl = null;
		users acc = userRepository.getById(req.getRemoteUser());
		if(order.getPhone().isEmpty() || order.getAddress().isEmpty() || order.getPhone().length()>11 || order.getPhone().length()<9  ) {

			List<Cart> item = cartRepository.findByIdUser(req.getRemoteUser());
			
			Double tongTien = cartRepository.tongTien(req.getRemoteUser());
//			users acc = userRepository.getById(req.getRemoteUser());
			
		
			model.addAttribute("item", item);
			model.addAttribute("size", item.size());
			if(tongTien == null) {
				tongTien = (double) 0;
				model.addAttribute("tongTien", tongTien);
			}else {
				model.addAttribute("tongTien", tongTien);
			}
			model.addAttribute("acc", acc);
			model.addAttribute("order", new Orders());
			if(order.getPhone().isEmpty()) {		
			model.addAttribute("loiphone", "Vui lòng nhập số điện thoại");
			}
			if(order.getPhone().length()>11 || order.getPhone().length()<9  ) {
				model.addAttribute("loiphone", "Vui lòng nhập đúng số điện thoại");
			}
		
			if(order.getAddress().isEmpty()) {	
				model.addAttribute("loiaddress", "Vui lòng nhập địa chỉ");
			}
			
			return "nguoiDung/checkout";

		}else {
			
			order.setStatus("Đã đặt");
			ordersRepository.save(order);
			int id = order.getId();
			List<Cart> gio = cartRepository.findByIdUser(req.getRemoteUser());
			
			for(Cart cart:gio) {
				OrderDetail od = new OrderDetail();
				Product product = productRepository.findById(cart.getProduct().getId()).get();
				Orders ord = ordersRepository.getById(id);
				Category cate = categoryRepository.getById(product.getCategory().getId());

				
				FileUploadUtil file = new FileUploadUtil();
				file.historyImageProduct(cart.getImgProductCart(), app);
				
				od.setName(cart.getNameProductCart());
				od.setImage(cart.getImgProductCart());
				od.setPrice(cart.getPriceProductCart());
				od.setQuanlity(cart.getQuanlityProductCart());
				od.setNamecate(cate.getNameCategory());
				od.setOrder(ord);
				
				orderDetailRepository.save(od); 
				int total = product.getQuantity() -cart.getQuanlityProductCart();

				if(total <= 0) {
					product.setAvailable(false);
					productRepository.save(product);
				}else {
					product.setQuantity(total);
					productRepository.save(product);
				}
				
				
				// KHÚC NÀY LÀ KHÚC THANH TOÁN. CẤM ĐỤNG VÀO
				VNPayConfiguration config = new VNPayConfiguration();
				//test sử lý vnpay
				String vnp_Version = "2.1.0";
		        String vnp_Command = "pay";
		        String vnp_OrderInfo = acc.getId() + " " + ord.getId() ;
		        String orderType = "110004";
		        String vnp_TxnRef = VNPayConfiguration.getRandomNumber(8);
		        String vnp_IpAddr = VNPayConfiguration.getIpAddress(req);
		        String vnp_TmnCode = VNPayConfiguration.vnp_TmnCode; 
		        
		        int amount = Math.round(ord.getAmount()) * 100;
		        Map<String, String> vnp_Params = new HashMap<>();
		        vnp_Params.put("vnp_Version", vnp_Version);
		        vnp_Params.put("vnp_Command", vnp_Command);
		        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
		        vnp_Params.put("vnp_Amount", String.valueOf(amount));
		        vnp_Params.put("vnp_CurrCode", "VND");
		        vnp_Params.put("vnp_BankCode", null);
		        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
		        vnp_Params.put("vnp_OrderInfo", vnp_OrderInfo);
		        vnp_Params.put("vnp_OrderType", orderType);
		        vnp_Params.put("vnp_Locale", "vn");
		        vnp_Params.put("vnp_ReturnUrl", VNPayConfiguration.vnp_Returnurl); 
		        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);
		        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
		        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		        String vnp_CreateDate = formatter.format(cld.getTime());
		        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
		        cld.add(Calendar.MINUTE, 15);
		        String vnp_ExpireDate = formatter.format(cld.getTime());
		        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);
		        vnp_Params.put("vnp_Bill_Mobile", acc.getPhone());
		        vnp_Params.put("vnp_Bill_Email", acc.getEmail());
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
		        String vnp_SecureHash = VNPayConfiguration.hmacSHA512(VNPayConfiguration.vnp_HashSecret, hashData.toString());
		        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
		        String paymentUrl = VNPayConfiguration.vnp_PayUrl + "?" + queryUrl;
		        JsonObject job = new JsonObject();
		        job.addProperty("code", "00");
		        job.addProperty("message", "success");
		        job.addProperty("data", paymentUrl);
		        Gson gson = new Gson();
		        resp.getWriter().write(gson.toJson(job));
		        System.err.println(paymentUrl);
				productRepository.save(product);	
				cartService.clear(cart.getId());
				vnpayUrl = paymentUrl;
			}
		
			return "redirect:" + vnpayUrl;
	
		}
	}	
}

package com.DATN.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.DATN.Entity.OrderDetail;
import com.DATN.Repository.OrderDetailRepository;

public class OrderDetailServiceImpl implements OrderDetailService {
	@Autowired
	OrderDetailRepository orderDetailRepository;
	@Override
	public OrderDetail save(OrderDetail orderdetail) {
		return orderDetailRepository.save(orderdetail);
	}

}

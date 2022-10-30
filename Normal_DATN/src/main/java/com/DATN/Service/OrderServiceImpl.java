package com.DATN.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.DATN.Entity.Orders;
import com.DATN.Repository.OrdersRepository;

public class OrderServiceImpl implements OrderService  {
 
	@Autowired
	OrdersRepository ordersRepository;
	@Override
	public Orders save(Orders order) {
		return ordersRepository.save(order);
	}

}

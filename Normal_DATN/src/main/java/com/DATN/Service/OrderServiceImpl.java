package com.DATN.Service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DATN.Entity.Orders;
import com.DATN.Repository.OrdersRepository;
@Service
public class OrderServiceImpl implements OrderService  {
 
	@Autowired
	OrdersRepository ordersRepository;
	@Override
	public Orders save(Orders order) {
		return ordersRepository.save(order);
	}
	@Override
	public Orders update(Integer id) {
	Orders order = ordersRepository.getById(id);
	order.setStatus("Cancelled");
	ordersRepository.save(order);
		return order;
	}
	@Override
	public List<Orders> findAll() {
		// TODO Auto-generated method stub
		return ordersRepository.findAll();
	}
	
}

package com.DATN.Service;


import java.util.List;

import com.DATN.Entity.Orders;

public interface OrderService {

	Orders save (Orders order);
	Orders update(Integer id);
	List<Orders> findAll();
	
}

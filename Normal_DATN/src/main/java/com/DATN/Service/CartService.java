package com.DATN.Service;

import java.util.Collection;

import com.DATN.Entity.Cart;


public interface CartService {
 
	double getAmount();

	int getCount();

	Collection<Cart> getAll();

	void clear();

	Cart update(Integer id, Integer qty);

	void remove(Integer id);

	void add(Cart cart);
}

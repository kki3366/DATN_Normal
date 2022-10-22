package com.DATN.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DATN.Entity.Cart;
import com.DATN.Repository.CartRepository;





@Service
public class CartServiceImpl implements  CartService {
	@Autowired 
	CartRepository cartRepository;
	@Override
	public double getAmount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Collection<Cart> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clear() {
	cartRepository.deleteAll();
		
	}

	@Override
	public Cart update(Integer id, Integer qty) {
		Cart item =  cartRepository.getById(id);
		item.setQuanlityProductCart(qty);
		cartRepository.save(item);
		return item;
	}

	@Override
	public void remove(Integer id) {
	cartRepository.deleteById(id);
	}
	@Override
	public void add(Cart item) {
	Cart	 item2 = cartRepository.findByMaSP(item.getProduct().getId());

		if(item2 == null) {
			cartRepository.save(item);
		}else {
			item2.setQuanlityProductCart(item2.getQuanlityProductCart()+1);
			cartRepository.save(item2);
		}
	
	}

	
}

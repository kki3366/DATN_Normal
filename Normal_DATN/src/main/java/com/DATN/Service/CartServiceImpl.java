//package com.DATN.Service;
//
//import java.util.Collection;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.DATN.Entity.Cart;
//import com.DATN.Repository.CartRepository;
//
//
//
//
//@Service
//public class CartServiceImpl implements  CartService {
//	@Autowired 
//	CartRepository cartRepository;
//	@Override
//	public double getAmount() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public int getCount() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public Collection<Cart> getAll() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void clear() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public Cart update(Integer id, Integer qty) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void remove(Integer id) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void add(Cart item) {
//		Item item2 = map.get(item.getId());
//
//		if(item2 == null) {
//			map.put(item.getId(), item);
//		}else {
//			item2.setQty(item2.getQty()+1);
//		}
//		//
//	}
//
//	
//}

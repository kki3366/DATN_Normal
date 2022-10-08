//package com.DATN.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.DATN.Entity.Cart;
//import com.DATN.Repository.CartRepository;
//
//@Service
//public class CartServiceImpl implements  CartService {
//
//	@Autowired
//	CartRepository cartRepository;
//	
//	@Override
//	public Cart saveCartService(Cart cart) {
//		return cartRepository.save(cart);
//	
//	}
//
//	@Override
//	public List<Cart> findAllCartService() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<Cart> findCartByNameService(String cartName) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void deleteCartById(int id) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public Optional<Cart> findByIdCart(int id) {
//		// TODO Auto-generated method stub
//		return Optional.empty();
//	}
//
//}

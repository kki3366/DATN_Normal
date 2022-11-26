package com.DATN.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.DATN.Entity.Cart;



public interface CartRepository extends JpaRepository<Cart, Integer>{
	@Query("SELECT o FROM Cart o WHERE o.product.id=?1 and o.user.id=?2")
    Cart  findByMaSP(Integer id, String user);
	
	@Query("SELECT SUM(o.priceProductCart * o.quanlityProductCart) FROM Cart o WHERE o.user.id=?1")
    Double  tongTien(String user);
	
	@Query("SELECT o FROM Cart o WHERE o.user.id=?1")
    List<Cart>  findByIdUser(String user);
	
	
	@Query("DELETE FROM Cart o WHERE o.user.id=?1")
    Cart  deleteByIdUser(String id);
	
	@Modifying
	@Transactional
	@Query(value = "delete from Carts where idProduct =:productId", nativeQuery = true)
	void deleteByProductsId(@Param("productId") int productId);
	
}

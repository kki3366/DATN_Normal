package com.DATN.Repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.DATN.Entity.Report.ReportByInventory;
import com.DATN.Entity.Report.ReportByPopularProduct;
import com.DATN.Entity.Report.ReportByRevenueByCustomer;
import com.DATN.Entity.Report.ReportByRevenueByYear;
import com.DATN.Entity.Report.ReportRevenueByCategory;
import com.DATN.Entity.Report.ReportRevenueByMonth;
import com.DATN.Entity.Report.ReportRevenueByProduct;
import com.DATN.Entity.Report.ReportRevenueByQuarter;

@Repository
public class ReportRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	public List<ReportByInventory> reportByInventory() {
		String sql = "select NEW " + ReportByInventory.class.getName() + " (c.nameCategory, "
				+ " SUM(CASE WHEN p.available = 'true' THEN p.quantity ELSE 0 END), "
				+ " SUM(CASE WHEN p.available = 'true' THEN (p.price*p.quantity) ELSE 0 END),"
				+ " MIN(CASE WHEN p.available = 'true' THEN p.price END),"
				+ "MAX(CASE WHEN p.available = 'true' THEN p.price END),"
				+ "AVG(CASE WHEN p.available = 'true' THEN p.price END))"
				+" from Product p join p.category c group by c.nameCategory";
		TypedQuery<ReportByInventory> query = entityManager.createQuery(sql, ReportByInventory.class);
		List<ReportByInventory> list = query.getResultList();
		return list;
	}
	
	public List<ReportByRevenueByCustomer> reportByRevenueByCustomers(){
		String sql = "select new " + ReportByRevenueByCustomer.class.getName() +" (users.id,"
				+ " SUM(CASE WHEN (ord.status like :status) THEN ordd.quanlity else 0 end), "
				+ "SUM(CASE WHEN (ord.status like :status) THEN (ordd.quanlity * ordd.price) else 0 end), "
				+ "MIN(CASE WHEN (ord.status like :status) THEN NULLIF(ordd.price,0) end),"
				+ " MAX(CASE WHEN (ord.status like :status) THEN ordd.price else 0 end), "
				+ "AVG(CASE WHEN (ord.status like :status) THEN NULLIF(ordd.price,0) end))"
				+ " from OrderDetail ordd join ordd.order ord join ord.user users group by users.id";
		TypedQuery<ReportByRevenueByCustomer> query = entityManager.createQuery(sql,ReportByRevenueByCustomer.class).setParameter("status", "%" + "???? giao" + "%");
		List<ReportByRevenueByCustomer> list = query.getResultList();
		return list;
	}
	
	public List<ReportRevenueByCategory> reportRevenueByCategories(){
		String sql = "select new " + ReportRevenueByCategory.class.getName() + " (ordd.namecate, "
				+ "	SUM(CASE WHEN (ord.status like :status) THEN ordd.quanlity else 0 end), "
				+ "	SUM(CASE WHEN (ord.status like :status) THEN (ordd.quanlity * ordd.price) else 0 end), "
				+ "	MIN(CASE WHEN (ord.status like :status) THEN NULLIF(ordd.price,0) end),"
				+ " MAX(CASE WHEN (ord.status like :status) THEN ordd.price else 0 end), "
				+ "	AVG(CASE WHEN (ord.status like :status) THEN NULLIF(ordd.price,0) end))"
				+ " from OrderDetail ordd join ordd.order ord group by ordd.namecate";
		TypedQuery<ReportRevenueByCategory> query = entityManager.createQuery(sql, ReportRevenueByCategory.class).setParameter("status", "%" + "???? giao" + "%");
		List<ReportRevenueByCategory> list = query.getResultList();
		return list;
	}
	
	public List<ReportByPopularProduct> byPopularProducts(){
		String sql = "select new " + ReportByPopularProduct.class.getName() + " (ordd.namecate,ordd.name,"
				+ " SUM(CASE WHEN (ord.status like :status) THEN ordd.quanlity else 0 end))"
				+ " from OrderDetail ordd join ordd.order ord group by ordd.namecate,ordd.name";
		TypedQuery<ReportByPopularProduct> query = entityManager.createQuery(sql,ReportByPopularProduct.class).setParameter("status", "%" + "???? giao" + "%");
		List<ReportByPopularProduct> list =query.getResultList();
		return list;
	}
	
	public List<ReportRevenueByProduct> reportRevenueByProducts(){
		String key = "???? giao";
		String sql = "select new " + ReportRevenueByProduct.class.getName() + " (ordd.name, "
				+ " SUM(CASE WHEN (ord.status like :status1) THEN ordd.quanlity ELSE 0 end),"
				+ " COUNT(CASE WHEN (ord.status like :status1) THEN ord.id end), "
				+ " SUM(CASE WHEN (ord.status like :status1) THEN (ordd.quanlity * ordd.price) ELSE 0 end))"
				+ " from OrderDetail ordd join ordd.order ord group by ordd.name";
		TypedQuery<ReportRevenueByProduct> query = entityManager.createQuery(sql, ReportRevenueByProduct.class).setParameter("status1", "%" + key + "%");
		List<ReportRevenueByProduct> list = query.getResultList();
		return list;
	}
	
	public List<ReportByRevenueByYear> reportByRevenueByYears(){
		String sql = "select new " + ReportByRevenueByYear.class.getName() + " (year(ord.orderDate), "
				+ " SUM(CASE WHEN (ord.status like :status) THEN ordd.quanlity ELSE 0 end), "
				+ " SUM(CASE WHEN (ord.status like :status) THEN (ordd.quanlity * ordd.price) ELSE 0 end), "
				+ " MIN(CASE WHEN (ord.status like :status) THEN NULLIF(ordd.price,0) end), "
				+ " MAX(CASE WHEN (ord.status like :status) THEN ordd.price ELSE 0 end), "
				+ " AVG(CASE WHEN (ord.status like :status) THEN NULLIF(ordd.price,0) end))"
				+ " from OrderDetail ordd join ordd.order ord group by year(ord.orderDate)";
		TypedQuery<ReportByRevenueByYear> query = entityManager.createQuery(sql,ReportByRevenueByYear.class).setParameter("status", "%" + "???? giao" + "%");
		List<ReportByRevenueByYear> list = query.getResultList();
		return list;
	}
	
	
	public List<ReportRevenueByQuarter> reportRevenueByQuarters(){
		String sql = "select new " + ReportRevenueByQuarter.class.getName() + " (year(ord.orderDate),CEILING((MONTH(ord.orderDate)+1)/3), "
				+ "SUM(CASE WHEN (ord.status like :status) THEN ordd.quanlity ELSE 0 end),"
				+ "SUM(CASE WHEN (ord.status like :status) THEN (ordd.quanlity * ordd.price) ELSE 0 end), "
				+ "MIN(CASE WHEN (ord.status like :status) THEN NULLIF(ordd.price,0) end), "
				+ "MAX(CASE WHEN (ord.status like :status) THEN ordd.price ELSE 0 end), "
				+ "AVG(CASE WHEN (ord.status like :status) THEN NULLIF(ordd.price,0) end))"
				+" from OrderDetail ordd join ordd.order ord group by year(ord.orderDate),CEILING((MONTH(ord.orderDate)+1)/3)";
		TypedQuery<ReportRevenueByQuarter> query = entityManager.createQuery(sql,ReportRevenueByQuarter.class).setParameter("status", "%" + "???? giao" + "%");
		List<ReportRevenueByQuarter> list = query.getResultList();
		return list;
	}
	
	public List<ReportRevenueByMonth> reportRevenueByMonths(){
		String sql = "select new " + ReportRevenueByMonth.class.getName() + " (year(ord.orderDate),MONTH(ord.orderDate), "
				+ "SUM(CASE WHEN (ord.status like :status) THEN ordd.quanlity ELSE 0 end),"
				+ "SUM(CASE WHEN (ord.status like :status) THEN (ordd.quanlity * ordd.price) ELSE 0 end), "
				+ "MIN(CASE WHEN (ord.status like :status) THEN NULLIF(ordd.price,0) end), "
				+ "MAX(CASE WHEN (ord.status like :status) THEN ordd.price ELSE 0 end), "
				+ "AVG(CASE WHEN (ord.status like :status) THEN NULLIF(ordd.price,0) end))"
				+" from OrderDetail ordd join ordd.order ord group by year(ord.orderDate),MONTH(ord.orderDate)";
		TypedQuery<ReportRevenueByMonth> query = entityManager.createQuery(sql,ReportRevenueByMonth.class).setParameter("status", "%" + "???? giao" + "%");
		List<ReportRevenueByMonth> list = query.getResultList();
		return list;
	}
	
}

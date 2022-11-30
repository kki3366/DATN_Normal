package com.DATN.Repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.DATN.Entity.Report.ReportByInventory;

@Repository
public class ReportRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	public List<ReportByInventory> reportByInventory() {
		String sql = "select NEW " + ReportByInventory.class.getName() + " (c.nameCategory, SUM(p.quantity),SUM(p.price*p.quantity),MIN(p.price),MAX(p.price),AVG(p.price))"
				+" from Product p join p.category c group by c.nameCategory";
		TypedQuery<ReportByInventory> query = entityManager.createQuery(sql, ReportByInventory.class);
		List<ReportByInventory> list = query.getResultList();
		return list;
	}
}

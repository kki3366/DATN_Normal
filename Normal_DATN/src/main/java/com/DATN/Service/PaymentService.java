package com.DATN.Service;

import com.DATN.Entity.Payment;

public interface PaymentService {

	Payment savePayment(Payment payment);
	
	Payment findByOrderId(int id);
}

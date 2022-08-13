package org.example.repository;

import org.example.model.Payment;

import java.util.ArrayList;
import java.util.List;

public class PaymentRepository {
    public List<Payment> payments = new ArrayList<>();

    public PaymentRepository() {
        this.payments = new ArrayList<>();
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public double getPaymentValue(int year, int month, int delivererId) {

        double sum = this.payments
                .stream()
                .mapToDouble(Payment::getAmount)
                .sum();
        return sum;

    }

    public void addPayment(Payment payment) {
        payments.add(payment);
    }

    public boolean existPayment(int delivererId) {
        return payments.stream()
                .filter(d1 -> delivererId == d1.getDelivererId())
                .findAny()
                .orElse(null)
                == null;
    }
}

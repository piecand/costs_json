package org.example.controler;

import org.example.model.Payment;

import java.util.Scanner;

public class PaymentController {
    private final Payment payment = new Payment();
    public Payment setPayment(Scanner scan) {

        System.out.println("New payment");
        System.out.printf("Year        : ");
        payment.setYear(scan.nextInt());
        System.out.printf("Month       : ");
        payment.setMonth(scan.nextInt());
        System.out.printf("Day         : ");
        payment.setDay(scan.nextInt());
        System.out.printf("DelivererId : ");
        payment.setDelivererId(scan.nextInt());
        System.out.printf("Amount      : ");
        payment.setAmount(scan.nextDouble());
        System.out.printf("Title       : ");
        payment.setTitle(scan.next());
        System.out.println();

        return payment;
    }
}

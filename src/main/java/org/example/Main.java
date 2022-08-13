package org.example;


import org.example.controler.DelivererController;
import org.example.controler.JsonFile;
import org.example.model.Deliverer;
import org.example.model.Payment;
import org.example.repository.DelivererRepository;
import org.example.repository.PaymentRepository;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        DelivererRepository delivererRepository = new DelivererRepository();
        PaymentRepository paymentRepository = new PaymentRepository();
        DelivererController delivererController = new DelivererController();


        Deliverer deliverer1 = new Deliverer("SOLID", "Solid Sp  z o.o.", "52 1240 6960 2022 0000 0048 9005");
        Deliverer deliverer2 = new Deliverer("ORANGE", "Orange Polska S.A.", "40 1140 1241 1068 0000 1810 7926");

        delivererRepository.addDeliver(deliverer1);
        delivererRepository.addDeliver(deliverer2);

        Payment payment1 = new Payment(2022, 5, 12, 1, 30.00d, "monthly payment");
        paymentRepository.addPayment(payment1);
        paymentRepository.addPayment(payment1);
        paymentRepository.addPayment(payment1);


        System.out.println("Number of deliverers: " + delivererRepository.deliverers.size());
        for (Deliverer d1 : delivererRepository.getDeliverers()) {
            System.out.println(d1);
        }

        System.out.println("Number of payments: " + paymentRepository.payments.size());
        for (Payment p1 : paymentRepository.getPayments()) {
            System.out.println(p1);
            System.out.println(delivererRepository.getDelivererById(p1.getDelivererId()));
        }

        System.out.println("Value for deliver no 1 in 2022 on 5: " + paymentRepository.getPaymentValue(2022, 5, 1));

        JsonFile outputFile = new JsonFile();
        outputFile.writeJsonFile(delivererRepository, paymentRepository, "dane.json");

        outputFile.readJsonFile(delivererRepository, paymentRepository, "dane.json");

        if (delivererRepository.deliverers.size() > 0) {
            System.out.println(delivererRepository.deliverers.size());
        } else {
            System.out.println("Deliverer Repository is empty!");
        }
        if (paymentRepository.payments.size() > 0) {
            System.out.println(paymentRepository.payments.size());
        } else {
            System.out.println("Payment Repository is empty!");
        }
    }
}
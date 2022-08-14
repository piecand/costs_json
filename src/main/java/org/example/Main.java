package org.example;


import org.example.controler.DelivererController;
import org.example.controler.JsonFile;
import org.example.controler.PaymentController;
import org.example.model.Deliverer;
import org.example.model.Payment;
import org.example.repository.DelivererRepository;
import org.example.repository.PaymentRepository;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        DelivererRepository delivererRepository = new DelivererRepository();
        PaymentRepository paymentRepository = new PaymentRepository();
        DelivererController delivererController = new DelivererController();
        PaymentController paymentController = new PaymentController();

        JsonFile outputFile = new JsonFile();

        printAll(delivererRepository, paymentRepository);

       outputFile.readJsonFile(delivererRepository, paymentRepository, "dane.json");

       printAll(delivererRepository, paymentRepository);

        // input from console
        Scanner scan = new Scanner(System.in).useDelimiter("\\n");
        delivererRepository.addDeliver(delivererController.setDeliverer(scan));
        paymentRepository.addPayment(paymentController.setPayment(scan));
        scan.close();

        outputFile.writeJsonFile(delivererRepository, paymentRepository, "dane.json");

        printAll(delivererRepository, paymentRepository);
    }


    private static void printAll(DelivererRepository delivererRepository, PaymentRepository paymentRepository) {
        System.out.println("noId = " + Deliverer.getNoId());
        if (delivererRepository.deliverers.size() > 0) {
            System.out.println(delivererRepository.deliverers.size());
            for (Deliverer dl: delivererRepository.getDeliverers()) {
                System.out.println(dl.toString());
            }
        } else {
            System.out.println("Deliverer Repository is empty!");
        }
        if (paymentRepository.payments.size() > 0) {
            System.out.println(paymentRepository.payments.size());
            for (Payment pa : paymentRepository.getPayments()) {
                System.out.println(pa.toString());
            }
        } else {
            System.out.println("Payment Repository is empty!");
        }
    }
}
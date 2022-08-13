package org.example.controler;

import org.example.model.Deliverer;

import java.util.Scanner;

public class DelivererController {
    private final Deliverer deliverer = new Deliverer();

     public Deliverer setDeliverer() {
        Scanner scan = new Scanner(System.in).useDelimiter("\\n");
        System.out.println("New deliverer:");
        System.out.printf("Name        : ");
        deliverer.setName(scan.next());
        System.out.println();
        System.out.printf("Short name  : ");
        deliverer.setShortName(scan.next());
        System.out.println();
        System.out.printf("Bank account: ");
        deliverer.setBankAccount(scan.next("[0-9]{2} [0-9]{4} [0-9]{4} [0-9]{4} [0-9]{4} [0-9]{4}"));
        scan.close();
        return deliverer;
    }


}

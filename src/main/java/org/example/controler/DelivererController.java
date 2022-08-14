package org.example.controler;

import org.example.model.Deliverer;

import java.util.Scanner;

public class DelivererController {
    private final Deliverer deliverer = new Deliverer();

     public Deliverer setDeliverer(Scanner scan) {

        System.out.println("New deliverer");
        System.out.printf("Name        : ");
        deliverer.setName(scan.next());
        System.out.printf("Short name  : ");
        deliverer.setShortName(scan.next());
        System.out.printf("Bank account: ");
        deliverer.setBankAccount(scan.next());
        System.out.println();

        // increase Deliver.noId during adding
        int tempId = Deliverer.getNoId();
        deliverer.setId(++tempId);

        Deliverer.setNoId(tempId);
        return deliverer;
    }


}

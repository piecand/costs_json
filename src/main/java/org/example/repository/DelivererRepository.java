package org.example.repository;

import org.example.model.Deliverer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DelivererRepository {
    public List<Deliverer> deliverers = new ArrayList<>();

    public DelivererRepository() {
        // read from JSON file
    }

    public List<Deliverer> getDeliverers() {
        return deliverers;
    }

    public Deliverer getDelivererById(int id) {
        // search for deliverer by id
        Deliverer deliverer = deliverers.stream()
                .filter(d1 -> id == d1.getId())
                .findAny()
                .orElse(null);
        return deliverer;
    }

    public void addDeliver(final Deliverer deliverer) {
        // add new deliverer
        deliverers.add(deliverer);
    }

    public boolean delDeliverer(int delivererId, PaymentRepository repository) {
        // delete existing and not with payment deliver
        return (getDelivererById(delivererId) != null) && repository.existPayment(delivererId);
    }

}

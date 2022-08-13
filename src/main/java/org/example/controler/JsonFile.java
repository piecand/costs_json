package org.example.controler;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.example.model.Deliverer;
import org.example.model.Payment;
import org.example.repository.DelivererRepository;
import org.example.repository.PaymentRepository;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JsonFile {

    int noId = 0;
    public void writeJsonFile(DelivererRepository delivererRepository,
                              PaymentRepository paymentRepository,
                              String name) throws IOException {

        ObjectMapper delivererMapper = new ObjectMapper();
        delivererMapper.enable(SerializationFeature.INDENT_OUTPUT);
        FileOutputStream fileDeliverer = new FileOutputStream(name + ".del");
        delivererMapper.writeValue(fileDeliverer, delivererRepository.deliverers);
        fileDeliverer.close();

        ObjectMapper paymentMapper = new ObjectMapper();
        paymentMapper.enable(SerializationFeature.INDENT_OUTPUT);
        FileOutputStream filePayment = new FileOutputStream(name + ".pay");
        delivererMapper.writeValue(filePayment, paymentRepository.payments);
        filePayment.close();

    }

    public void readJsonFile(DelivererRepository delivererRepository,
                             PaymentRepository paymentRepository,
                             String name) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

        InputStream fileDeliverer = new FileInputStream(name + ".del");
        delivererRepository.deliverers.clear();
        delivererRepository.deliverers = mapper.readValue(fileDeliverer, List.class);

        fileDeliverer.close();

        InputStream filePayment = new FileInputStream(name + ".pay");
        paymentRepository.payments.clear();
        paymentRepository.payments = mapper.readValue(filePayment, List.class);
        filePayment.close();

    }

}


package org.example.controler;

import org.example.model.Deliverer;
import org.example.model.Payment;
import org.example.repository.DelivererRepository;
import org.example.repository.PaymentRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JsonFile {

    public void writeJsonFile(DelivererRepository delivererRepository,
                              PaymentRepository paymentRepository,
                              String name) {

        JSONObject jsonObject = new JSONObject();
        JSONArray jasonDelivererArray = new JSONArray();
        JSONArray jasonPaymentArray = new JSONArray();

        for (Deliverer d : delivererRepository.getDeliverers()) {
            jasonDelivererArray.add(d.toJason());
        }

        for (Payment p : paymentRepository.getPayments()) {
            jasonPaymentArray.add(p.toJason());
        }

        jsonObject.put("Deliverer", jasonDelivererArray);
        jsonObject.put("Payment", jasonPaymentArray);

        try (FileWriter file = new FileWriter(name)) {
            file.write(jsonObject.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readJsonFile(DelivererRepository delivererRepository,
                             PaymentRepository paymentRepository,
                             String name) {

        try (FileReader reader = new FileReader(name)) {
            JSONObject jsonObject = new JSONObject();
            JSONArray jsonDelivererList = new JSONArray();
            JSONArray jsonPaymentList = new JSONArray();
            JSONParser jsonParser = new JSONParser();

            jsonObject = (JSONObject) jsonParser.parse(reader);

            jsonDelivererList = (JSONArray) jsonObject.get("Deliverer");
            System.out.println("No deliverer: " +jsonDelivererList.size());
            for (Object d : jsonDelivererList) {
                System.out.println(d.);

            }

            jsonPaymentList = (JSONArray) jsonObject.get("Payment");
            System.out.println("No payment: " + jsonPaymentList.size());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

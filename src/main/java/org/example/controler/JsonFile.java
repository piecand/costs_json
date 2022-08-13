package org.example.controler;

import org.example.model.Deliverer;
import org.example.model.Payment;
import org.example.repository.DelivererRepository;
import org.example.repository.PaymentRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JsonFile {

    int noId = 0;
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
            JSONObject jsonObject;
            JSONArray jsonDelivererList;
            JSONArray jsonPaymentList;

            JSONParser jsonParser = new JSONParser();

            if (delivererRepository.deliverers.size() > 0) {
                delivererRepository.deliverers.clear();
            }

            if (paymentRepository.payments.size() > 0) {
                paymentRepository.payments.clear();
            }

            jsonObject = (JSONObject) jsonParser.parse(reader);

            jsonDelivererList = (JSONArray) jsonObject.get("Deliverer");
            jsonDelivererList.forEach(del -> delivererRepository.addDeliver(parseDelivererObject((JSONObject) del)));

            jsonPaymentList = (JSONArray) jsonObject.get("Payment");
            jsonPaymentList.forEach(del -> paymentRepository.addPayment(parsePaymentObject((JSONObject) del)));


        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private Payment parsePaymentObject(JSONObject del) {
        return new Payment(
                ((Long) del.get("year")).intValue(),
                ((Long) del.get("month")).intValue(),
                ((Long) del.get("day")).intValue(),
                ((Long)  del.get("delivererId")).intValue(),
                (Double) del.get("amount"),
                (String) del.get("title"));
    }

    private Deliverer parseDelivererObject(JSONObject del) {
        Deliverer deliverer = new Deliverer(del.get("shortName").toString(),
                del.get("name").toString(),
                del.get("bankAccount").toString());
        Long idL = (Long) del.get("id");
        int id = idL.intValue();
        deliverer.setId(id);
        if (id > noId) {
            noId = id;
        }
        return deliverer;
    }
}


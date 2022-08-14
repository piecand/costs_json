package org.example.controler;

import org.example.model.Deliverer;
import org.example.model.Payment;
import org.example.repository.DelivererRepository;
import org.example.repository.PaymentRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class JsonFile {
    public static final Logger logger = LoggerFactory.getLogger(JsonFile.class);

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

        try {
            FileWriter fileWriter = new FileWriter(name);
            fileWriter.write(jsonObject.toJSONString());
            fileWriter.flush();
            fileWriter.close();
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
            jsonDelivererList.forEach(del -> delivererRepository.addDeliver(parseDelivererObject((JSONObject) del)));

            jsonPaymentList = (JSONArray) jsonObject.get("Payment");
            jsonPaymentList.forEach(del -> paymentRepository.addPayment(parsePaymentObject((JSONObject) del)));


        } catch (FileNotFoundException e) {
            logger.warn("File not exist!");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            logger.info("JSON parsing error!");
        }
    }

    private Payment parsePaymentObject(JSONObject del) {
        Payment payment = new Payment(
                ((Long) del.get("year")).intValue(),
                ((Long) del.get("month")).intValue(),
                ((Long) del.get("day")).intValue(),
                ((Long)  del.get("delivererId")).intValue(),
                (Double) del.get("amount"),
                (String) del.get("title"));
        return payment;
    }

    private Deliverer parseDelivererObject(JSONObject del) {
        Deliverer deliverer = new Deliverer(del.get("shortName").toString(),
                del.get("name").toString(),
                del.get("bankAccount").toString());
        int tempId = ((Long) del.get("id")).intValue();
        deliverer.setId(tempId);
        if (tempId > Deliverer.getNoId()){
            Deliverer.setNoId(tempId);
        }
        return deliverer;
    }
}


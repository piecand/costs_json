package org.example.model;

import org.json.simple.JSONObject;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Deliverer {

    private static int noId = 0;
    int id;
    String shortName;
    String name;
    String bankAccount;

    public Deliverer() {
    }

    public Deliverer(String shortName, String name, String bankAccount) {
        noId++;
        this.id = noId;
        this.shortName = shortName;
        this.name = name;
        this.bankAccount = bankAccount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public JSONObject toJason() {
        JSONObject deliverJson = new JSONObject();

        deliverJson.put("id", id);
        deliverJson.put("shortName", shortName);
        deliverJson.put("name", name);
        deliverJson.put("bankAccount", bankAccount);

        return deliverJson;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", shortName='" + shortName + '\'' +
                ", name='" + name + '\'' +
                ", bankAccount='" + bankAccount + '\'' +
                '}';
    }
}

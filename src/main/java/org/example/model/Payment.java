package org.example.model;

public class Payment {
    int year;
    int month;
    int day;
    int delivererId;
    double amount;
    String title;

    public Payment() {
    }

    public Payment(int year, int month, int day, int delivererId, double amount, String title) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.delivererId = delivererId;
        this.amount = amount;
        this.title = title;

    }


    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getDelivererId() {
        return delivererId;
    }

    public void setDelivererId(int delivererId) {
        this.delivererId = delivererId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", delivererId=" + delivererId +
                ", amount=" + amount +
                ", title=" + title +
                '}';
    }
}

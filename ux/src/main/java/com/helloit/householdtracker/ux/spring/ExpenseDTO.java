package com.helloit.householdtracker.ux.spring;

import java.util.Calendar;


public class ExpenseDTO {

    private String date;
    private double amount;
    private String description;



    public ExpenseDTO(String date, double amount, String description) {
        this.date = date;
        this.amount = amount;
        this.description = description;
    }



    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}




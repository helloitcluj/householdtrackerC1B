package com.helloit.householdtracker.ux.common.entities;


import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "Expense")


public class Expense {


    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    private double amount;

    private Integer userId;

    @Temporal (TemporalType.TIMESTAMP)
    private Calendar date;

    private String description;

    public Expense () {
    }

    public Expense(double amount, Calendar date, String description, Integer userId) {
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.userId = userId;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Calendar getCalendar() {
        return date;
    }

    public void setCalendar(Calendar date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

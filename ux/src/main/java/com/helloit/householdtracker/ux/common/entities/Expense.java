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

    @Temporal (TemporalType.TIMESTAMP)
    private Calendar date;

    private String description;
    private Integer userId;



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

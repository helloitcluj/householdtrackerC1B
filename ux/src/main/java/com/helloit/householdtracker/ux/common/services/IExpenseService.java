package com.helloit.householdtracker.ux.common.services;

import com.helloit.householdtracker.ux.common.entities.Expense;

import java.util.Calendar;

/**
 */
public interface IExpenseService {

    Expense save(Calendar date, double amount, String description, Integer userId);
}

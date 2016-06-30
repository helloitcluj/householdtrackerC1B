package com.helloit.householdtracker.ux.spring;

import com.helloit.householdtracker.ux.common.entities.Expense;
import com.helloit.householdtracker.ux.common.repository.IExpenseRepository;
import com.helloit.householdtracker.ux.common.services.IExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

/**
 */

@Service
public class ExpenseService implements IExpenseService {


    private final IExpenseRepository expenseRepository;

    @Autowired
    public ExpenseService(final IExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }


    @Override
    public Expense save(final Calendar date, final double amount, final String description, final Integer userId) {


        final Expense expense = new Expense(amount, date, description, userId);
        //final  Expense result = expenseRepository.save(expense);

        return expenseRepository.save(expense);
    }
}

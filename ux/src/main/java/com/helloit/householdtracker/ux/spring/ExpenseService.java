package com.helloit.householdtracker.ux.spring;

import com.helloit.householdtracker.ux.common.entities.Expense;
import com.helloit.householdtracker.ux.common.repository.IExpenseRepository;
import com.helloit.householdtracker.ux.common.services.IExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 */

@Service
public class ExpenseService implements IExpenseService {


    private final IExpenseRepository expenseRepository;
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

    @Autowired

    public ExpenseService(final IExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }


    @Override
    public Expense save(final String date, final double amount, final String description, final Integer userId) {

        final Expense expense = new Expense(amount, convert(date), description, userId);

        return expenseRepository.save(expense);
    }


    @Override
    public List<Expense> findByUserId(Integer id) {

        return expenseRepository.findByUserId(id);
    }


    private Calendar convert(final String dateAsString) {
        Calendar result = null;

        try {
            Date date = formatter.parse(dateAsString);
            result = Calendar.getInstance();
            result.setTime(date);
        } catch (final ParseException ignored) {
            //ignored
        }

        return result;    }
}

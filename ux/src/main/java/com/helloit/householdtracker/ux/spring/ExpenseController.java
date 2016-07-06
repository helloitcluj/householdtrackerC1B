package com.helloit.householdtracker.ux.spring;


import com.helloit.householdtracker.ux.common.SecurityFilter;
import com.helloit.householdtracker.ux.common.entities.Expense;
import com.helloit.householdtracker.ux.common.entities.User;
import com.helloit.householdtracker.ux.common.repository.IExpenseRepository;
import com.helloit.householdtracker.ux.common.services.IAccountService;
import com.helloit.householdtracker.ux.common.services.IExpenseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.xml.transform.Result;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping(path = "expense")
public class ExpenseController {
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

    @Autowired
    private IExpenseService expenseService;

    @Autowired
    private IAccountService accountService;


    @RequestMapping(path = "create", method = RequestMethod.POST)
    public
    @ResponseBody
    void create(final HttpSession session, final String date, final double amount, final String description) {

        final String name = (String) session.getAttribute(SecurityFilter.CURRENT_PRINCIPAL_TAG);
        final User user = accountService.find(name);

        expenseService.save(date, amount, description, user.getId());
    }


    @RequestMapping(path = "findAll", method = RequestMethod.POST)
    public
    @ResponseBody
    List <ExpenseDTO> findAll(final HttpSession session) {

        final String name = (String) session.getAttribute(SecurityFilter.CURRENT_PRINCIPAL_TAG);
        final User user = accountService.find(name);

        List<Expense> expenses = expenseService.findByUserId(user.getId());

        List<ExpenseDTO> result = new ArrayList<ExpenseDTO>(expenses.size());
        for (final  Expense expense:expenses){
            final Calendar date = expense.getCalendar();

            final String dateAsString = date == null ? null : formatter.format(date.getTime());
            result.add(new ExpenseDTO(dateAsString,expense.getAmount(), expense.getDescription()));
        }

        return result;
    }

}
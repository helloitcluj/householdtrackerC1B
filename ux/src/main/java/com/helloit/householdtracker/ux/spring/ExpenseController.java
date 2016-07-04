package com.helloit.householdtracker.ux.spring;


import com.helloit.householdtracker.ux.common.SecurityFilter;
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
import java.util.Calendar;
import java.util.Date;


@Controller
@RequestMapping(path = "expense")
public class ExpenseController {

    private static final Logger LOGGER = LogManager.getLogger(ExpenseController.class);

    @Autowired
    private IExpenseService expenseService;

    @Autowired
    private IAccountService accountService;


    @RequestMapping(path = "create", method = RequestMethod.POST)
    public @ResponseBody void create(final HttpSession session, final String date, final double amount, final String description){

        final String name = (String ) session.getAttribute(SecurityFilter.CURRENT_PRINCIPAL_TAG);
        final User user = accountService.find(name);

        expenseService.save(date, amount, description, user.getId());
    }
}
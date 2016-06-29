package com.helloit.householdtracker.ux.spring;


import com.helloit.householdtracker.ux.common.repository.IExpenseRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;


@Controller
@RequestMapping(path = "expense")
public class ExpenseController {

    private static final Logger LOGGER = LogManager.getLogger(ExpenseController.class);

    @Autowired
    private IExpenseRepository accountService;

    @RequestMapping(path = "create", method = RequestMethod.POST)
    public String create(final HttpSession session, final Calendar date, final double amount, final String description){

        return "";
    }
}
package com.helloit.householdtracker.ux.spring.repository;

import com.helloit.householdtracker.ux.common.repository.IExpenseRepository;
import com.helloit.householdtracker.ux.spring.AppConfig;
import com.helloit.householdtracker.ux.spring.WebConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, WebConfig.class})
@WebAppConfiguration

public class ExpenseRepositoryTest {

    @Autowired
    private IExpenseRepository expenseRepository;

    @Test
    public void basicTest(){


    }
}

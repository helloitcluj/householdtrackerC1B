package com.helloit.householdtracker.ux.spring.repository;

import com.helloit.householdtracker.tools.SchemaManager;
import com.helloit.householdtracker.ux.common.entities.Expense;
import com.helloit.householdtracker.ux.common.entities.User;
import com.helloit.householdtracker.ux.common.repository.IExpenseRepository;
import com.helloit.householdtracker.ux.common.repository.IUserRepository;
import com.helloit.householdtracker.ux.spring.AppConfig;
import com.helloit.householdtracker.ux.spring.WebConfig;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Calendar;

/**
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, WebConfig.class})
@WebAppConfiguration

public class ExpenseRepositoryTest {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IExpenseRepository expenseRepository;
    private User testUser;

    @Before
    public void setup(){

        final SchemaManager schemaManager = new SchemaManager();
        schemaManager.recreateSchema();

        final User user = new User();
        user.setUsername("Test");
        user.setPassword("1");

        testUser = userRepository.save(user);
    }

    @Test
    public void basicTest(){
        final Calendar now = Calendar.getInstance();
        final Expense expense = new Expense (32.5, now, "Chocolate", testUser.getId());

        Expense saved = expenseRepository.save(expense);

        Assert.assertEquals("Should have an id of 0", new Integer(0), saved.getId());
    }


    @Test
    public void foreignKeyTest(){
        final Calendar now = Calendar.getInstance();
        final Expense expense = new Expense (32.5, now, "Chocolate", testUser.getId());

        try {

            Expense saved = expenseRepository.save(expense);
            throw new UnsupportedOperationException("Should not happen!");
        } catch (RuntimeException ex) {
            //should fail
        }
    }
}

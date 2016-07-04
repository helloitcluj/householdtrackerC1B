package com.helloit.householdtracker.ux.spring;

import com.helloit.householdtracker.ux.common.entities.Expense;
import com.helloit.householdtracker.ux.common.repository.IExpenseRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Calendar;
import java.util.List;

/**
 */
public class ExpenseServiceTest {

    public static final double TEST_AMOUNT = 32.5;
    public static final String TEST_DESCRIPTION = "Chocolate";
    public static final Integer TEST_USER_ID = 1;

    @Test
    public void basicTest() {
        final MockExpenseRepository repository = createMockExpenseRepository();
        final ExpenseService expenseService = new ExpenseService(repository);

        final String date = "2016-07-04T15:00";
        expenseService.save(date, TEST_AMOUNT, TEST_DESCRIPTION, TEST_USER_ID);

        Expense savedEntity = repository.getSavedEntity();

        Assert.assertNotNull("Should be the same", savedEntity.getCalendar());
        Assert.assertEquals("Should be the same", TEST_AMOUNT, savedEntity.getAmount(), 0.0);
        Assert.assertEquals("Should be the same", TEST_DESCRIPTION, savedEntity.getDescription());
        Assert.assertEquals("Should be the same", TEST_USER_ID, savedEntity.getUserId());
    }

    private MockExpenseRepository createMockExpenseRepository() {
        return new MockExpenseRepository();
    }

    private static class MockExpenseRepository implements IExpenseRepository {

        private Expense savedEntity;

        @Override
        public List<Expense> findByUserId(String userId) {
            return null;
        }

        @Override
        public List<Expense> findAll() {
            return null;
        }

        @Override
        public List<Expense> findAll(Sort sort) {
            return null;
        }

        @Override
        public List<Expense> findAll(Iterable<Integer> integers) {
            return null;
        }

        @Override
        public <S extends Expense> List<S> save(Iterable<S> entities) {
            return null;
        }

        @Override
        public void flush() {
        }

        @Override
        public <S extends Expense> S saveAndFlush(S entity) {
            return null;
        }

        @Override
        public void deleteInBatch(Iterable<Expense> entities) {

        }

        @Override
        public void deleteAllInBatch() {

        }

        @Override
        public Expense getOne(Integer integer) {
            return null;
        }

        @Override
        public Page<Expense> findAll(Pageable pageable) {
            return null;
        }

        @Override
        public <S extends Expense> S save(S entity) {
            savedEntity = entity;

            return entity;
        }

        @Override
        public Expense findOne(Integer integer) {
            return null;
        }

        @Override
        public boolean exists(Integer integer) {
            return false;
        }

        @Override
        public long count() {
            return 0;
        }

        @Override
        public void delete(Integer integer) {

        }

        @Override
        public void delete(Expense entity) {

        }

        @Override
        public void delete(Iterable<? extends Expense> entities) {

        }

        @Override
        public void deleteAll() {

        }

        public Expense getSavedEntity() {
            return savedEntity;
        }
    }
}
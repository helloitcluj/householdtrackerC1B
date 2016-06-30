package com.helloit.householdtracker.ux.spring;

import com.helloit.householdtracker.ux.common.entities.Expense;
import com.helloit.householdtracker.ux.common.repository.IExpenseRepository;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Calendar;
import java.util.List;

/**
 */
public class ExpenseServiceTest {

    @Test
    public void basicTest() {
        final IExpenseRepository repository = createMockExpenseRepository();
        final ExpenseService expenseService = new ExpenseService(repository);

        final Calendar now = Calendar.getInstance();
        expenseService.save(now, 32.5, "Chocolate",1);
    }

    private MockExpenseRepository createMockExpenseRepository() {
        return new MockExpenseRepository();
    }

    private static class MockExpenseRepository implements IExpenseRepository {

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
            return null;
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
    }
}
package com.helloit.householdtracker.ux.common.repository;


import com.helloit.householdtracker.ux.common.entities.Expense;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Calendar;
import java.util.List;


public interface IExpenseRepository extends JpaRepository<Expense, Integer> {

    List<Expense> findByUserId(String userId);


}

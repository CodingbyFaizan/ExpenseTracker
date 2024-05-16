package org.example.dao;

import org.example.Expense;

import java.util.List;

public interface ExpenseDAO {

    void connection();
    void addExpense(Expense expense);
    void getExpenseById(int id);
    void updateExpense(int id,Expense expense);
    void deleteExpense(int id);
    List<Expense> getAllExpense();
    void closeConnection();

}

package org.example;

//import jdk.vm.ci.meta.Local;

import java.sql.Date;
import java.time.LocalDate;

public class Expense {
    private int id;
    private String desc;
    private Double amount;
    private LocalDate date;

    public Expense() {

    }

    public Expense(String desc, Double amount, LocalDate date) {
        this.desc = desc;
        this.amount = amount;
        this.date = date;
    }

    public Expense(int id, String desc, Double amount, LocalDate date) {
        this.id = id;
        this.desc = desc;
        this.amount = amount;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}

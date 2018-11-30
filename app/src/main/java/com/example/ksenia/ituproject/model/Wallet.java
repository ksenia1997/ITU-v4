package com.example.ksenia.ituproject.model;

import java.util.ArrayList;
import java.util.List;

public class Wallet {
    private List<Operation> operations;
    private String name;

    public Wallet(String name) {
        this(name, new ArrayList<Operation>());
    }

    public Wallet(String name, List<Operation> operations) {
        this.name = name;
        this.operations = operations;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Operation> getOperations() { return operations; }
    public void setOperations(List<Operation> operations) { this.operations = operations; }

    public float getBalance() {
        float balance = 0;
        for (Operation o : operations) {
            balance += o.getAmount();
        }
        return balance;
    }

    public Operation addIncomeOperation(float amount, Currency currency, String description) {
        Operation o = Operation.incomeOperation(amount, this, currency, description);
        operations.add(o);
        return o;
    }

    public Operation addIncomeOperation(float amount, String description) {
        return addIncomeOperation(amount, null, description);
    }

    public Operation addIncomeOperation(float amount) {
        return addIncomeOperation(amount, null);
    }


    public Operation addOutcomeOperation(float amount, Currency currency, Category category, String description) {
        Operation o = Operation.outcomeOperation(amount, this, currency, category, description);
        operations.add(o);
        return o;
    }

    public Operation addOutcomeOperation(float amount, Category category, String description) {
        return addOutcomeOperation(amount, null, category, description);
    }

    public Operation addOutcomeOperation(float amount, Category category) {
        return addOutcomeOperation(amount, category, null);
    }

}

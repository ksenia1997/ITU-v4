package com.example.ksenia.ituproject.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    public Operation addIncomeOperation(float amount, Currency currency, String description) {
        return Operation.incomeOperation(amount, this, currency, description);
    }

    public Operation addIncomeOperation(float amount, Currency currency) {
        return addIncomeOperation(amount, currency, null);
    }


    public Operation addOutcomeOperation(float amount, Currency currency, Category category, String description) {
        return Operation.outcomeOperation(amount, this, currency, category, description);
    }

    public Operation addOutcomeOperation(float amount, Currency currency, Category category) {
        return addOutcomeOperation(amount, currency, category, null);
    }

    public Operation getNthOperationFromEnd(int n) {
        return operations.get(operations.size() - 1 - n);
    }

    void removeOperation(Operation operation) {
        operations.remove(operation);
    }

    @Override
    public String toString() {
        return name;
    }

    String getCurrencySummary(List<Currency> currencies) {
        Map<String, Float> map = new LinkedHashMap<>(currencies.size());
        for (Currency currency : currencies) {
            map.put(currency.toString(), (float) 0);
        }
        for (Operation operation : operations) {
            String curr = operation.getCurrency().toString();
            map.put(curr, map.get(curr) + operation.getAmount());
        }

        boolean first = true;
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, Float> e : map.entrySet()) {
            if (!first) {
                builder.append(", ");
            } else {
                first = false;
            }
            builder.append(e.getValue().toString());
            builder.append(" ");
            builder.append(e.getKey());
        }

        return builder.toString();

    }

}

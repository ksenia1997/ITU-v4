package com.example.ksenia.ituproject.model;


// TODO Currency sa zatial nepouziva
public class Operation {

    private float amount;  // positive for incomes, negative for outcomes

    private Wallet wallet;  // ucel

    private Currency currency;

    private Category category;  // null pre input

    private String description;

    private Operation(float amount, Wallet wallet, Currency currency, Category category, String description) {
        this.amount = amount;
        this.wallet = wallet;
        this.currency = currency;
        this.category = category;
        this.description = description;
        wallet.getOperations().add(this);
        if (category != null) {
            category.addOperation(this);
        }
    }

    static public Operation incomeOperation(float amount, Wallet wallet, Currency currency, String description) {
        if (amount < 0) {
            amount = -amount;
        }
        return new Operation(amount, wallet, currency, null, description);
    }

    static public Operation outcomeOperation(float amount, Wallet wallet, Currency currency, Category category, String description) {
        if (amount > 0) {
            amount = -amount;
        }
        return new Operation(amount, wallet, currency, category, description);
    }

    public float getAmount() { return amount; }
    public Wallet getWallet() {return wallet; }
    public Currency getCurrency() { return currency; }
    public Category getCategory() { return category; }
    public String getDescription() { return description != null ? description : ""; }
    public boolean isIncome() { return amount > 0; }
    public boolean isOutcome() { return amount < 0; }


    /**
     * Remove this operation from its wallet and category.
     */
    public void remove() {
        wallet.removeOperation(this);
        wallet = null;

        if (category != null) {
            category.removeOperation(this);
            category = null;
        }

    }

    public float getAmountInMainCurrency() {
        if (currency.getDirection()) {
            return getAmount() / currency.getRate();
        } else {
            return getAmount() * currency.getRate();
        }
    }

}





package com.example.ksenia.ituproject.model;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.example.ksenia.ituproject.R;

import java.util.ArrayList;
import java.util.List;

public class Status {

    private Currency mainCurrency;

    private final List<Currency> currencies = new ArrayList<>();

    private final static List<Wallet> wallets = new ArrayList<>();

    // Promenna pro ukladani dat kategorii.
    private static ArrayList<Category> CategoriesData = new ArrayList<>();

    // Ukladani dat kategorii.
    public static void saveCategories(ArrayList<Category> data)
    {
        CategoriesData = data;
    }

    // Nacitani dat kategorii.
    public static ArrayList<Category> loadCategories()
    {
        return CategoriesData;
    }

    public Status(Context context) {

        // init Currencies
        Currency czkCurrency = new Currency("CZK");
        mainCurrency = czkCurrency;
        currencies.add(czkCurrency);
        currencies.add(new Currency("EUR"));

        // init Categories
        CategoriesData.add(new Category("Food", ContextCompat.getColor(context, R.color.colorDefaultFood)));
        CategoriesData.add(new Category("Smoking", ContextCompat.getColor(context, R.color.colorDefaultSmoking)));
        CategoriesData.add(new Category("Health", ContextCompat.getColor(context, R.color.colorDefaultHealth)));
        CategoriesData.add(new Category("Entertainment", ContextCompat.getColor(context, R.color.colorDefaultEntertainment)));

        // init Wallets
        Wallet homeWallet = new Wallet("Home");
        wallets.add(homeWallet);
        wallets.add(new Wallet("Business"));
        wallets.add(new Wallet("Savings"));

        // add Operations
        homeWallet.addIncomeOperation(40, currencies.get(0));
        homeWallet.addIncomeOperation(40, currencies.get(0));
        homeWallet.addIncomeOperation(40, currencies.get(0));
        homeWallet.addIncomeOperation(50, currencies.get(0));
        homeWallet.addOutcomeOperation(150, currencies.get(0), CategoriesData.get(0));
        homeWallet.addOutcomeOperation(150, currencies.get(0), CategoriesData.get(0));
        homeWallet.addOutcomeOperation(150, currencies.get(0), CategoriesData.get(0));
        homeWallet.addOutcomeOperation(150, currencies.get(0), CategoriesData.get(1));
        homeWallet.addOutcomeOperation(150, currencies.get(1), CategoriesData.get(2));
        homeWallet.addOutcomeOperation(150, currencies.get(1), CategoriesData.get(3));
        homeWallet.addOutcomeOperation(150, currencies.get(0), CategoriesData.get(2));
        homeWallet.addIncomeOperation(50, currencies.get(0));
        homeWallet.addOutcomeOperation(150, currencies.get(0), null);
        homeWallet.addOutcomeOperation(150, currencies.get(0), null);
        homeWallet.addIncomeOperation(50, currencies.get(0));
        homeWallet.addOutcomeOperation(150, currencies.get(0), null);
        homeWallet.addOutcomeOperation(150, currencies.get(0), null);
        homeWallet.addIncomeOperation(50, currencies.get(0));
        homeWallet.addOutcomeOperation(150, currencies.get(0), null);
        homeWallet.addOutcomeOperation(150, currencies.get(0), null);


    }

    public static List<Wallet> getWallets() { return wallets; }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public Currency getMainCurrency() {
        return mainCurrency;
    }

    public void setMainCurrency(Currency mainCurrency) {
        this.mainCurrency = mainCurrency;
    }

    /**
     * Remove wallet and remove its operations (removing operation removes it from category).
     * @param wallet Wallet to remove.
     */
    public void removeWallet(Wallet wallet) {
        List<Operation> operationList = new ArrayList<>(wallet.getOperations());
        for (Operation op : operationList) {
            op.remove();
        }
        wallets.remove(wallet);
    }
}

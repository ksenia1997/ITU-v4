package com.example.ksenia.ituproject.model;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.example.ksenia.ituproject.R;

import java.util.ArrayList;
import java.util.List;

public class Status {

    private final static List<Currency> currencies = new ArrayList<>();

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
        currencies.add(new Currency("EUR"));
        currencies.add(new Currency("CZK"));

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
        homeWallet.addIncomeOperation(40);
        homeWallet.addIncomeOperation(40);
        homeWallet.addIncomeOperation(40);
        homeWallet.addIncomeOperation(50);
        homeWallet.addOutcomeOperation(150, CategoriesData.get(0));
        homeWallet.addOutcomeOperation(150, CategoriesData.get(0));
        homeWallet.addOutcomeOperation(150, CategoriesData.get(0));
        homeWallet.addOutcomeOperation(150, CategoriesData.get(1));
        homeWallet.addOutcomeOperation(150, CategoriesData.get(2));
        homeWallet.addOutcomeOperation(150, CategoriesData.get(3));
        homeWallet.addOutcomeOperation(150, CategoriesData.get(2));
        homeWallet.addIncomeOperation(50);
        homeWallet.addOutcomeOperation(150, null);
        homeWallet.addOutcomeOperation(150, null);
        homeWallet.addIncomeOperation(50);
        homeWallet.addOutcomeOperation(150, null);
        homeWallet.addOutcomeOperation(150, null);
        homeWallet.addIncomeOperation(50);
        homeWallet.addOutcomeOperation(150, null);
        homeWallet.addOutcomeOperation(150, null);


    }

    public static List<Wallet> getWallets() { return wallets; }

    public static List<Currency> getCurrencies() {
        return currencies;
    }
}

package com.example.ksenia.ituproject.ui.activities;

import com.example.ksenia.ituproject.model.Category;
import com.example.ksenia.ituproject.model.Wallet;

import java.util.ArrayList;
import java.util.List;

public class Status {

    private final List<Wallet> wallets = new ArrayList<>();

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

    public Status() {
        Wallet homeWallet = new Wallet("Home");
        wallets.add(homeWallet);
        wallets.add(new Wallet("Business"));
        wallets.add(new Wallet("Savings"));

        homeWallet.addIncomeOperation(40);
        homeWallet.addIncomeOperation(40);
        homeWallet.addIncomeOperation(40);
        homeWallet.addIncomeOperation(50);
        homeWallet.addOutcomeOperation(150, null);
        homeWallet.addOutcomeOperation(150, null);
        homeWallet.addOutcomeOperation(150, null);
        homeWallet.addOutcomeOperation(150, null);
        homeWallet.addOutcomeOperation(150, null);
        homeWallet.addOutcomeOperation(150, null);
        homeWallet.addOutcomeOperation(150, null);
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

    public List<Wallet> getWallets() { return wallets; }
}

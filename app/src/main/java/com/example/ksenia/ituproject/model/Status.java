package com.example.ksenia.ituproject.model;

import com.example.ksenia.ituproject.model.Category;

import java.util.ArrayList;

public class Status {

    // Promenna pro ukladani dat kategorii.
    private static ArrayList<Category> CategoriesData = new ArrayList();

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

}

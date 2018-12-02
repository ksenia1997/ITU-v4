package com.example.ksenia.ituproject.ui.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.example.ksenia.ituproject.R;
import com.example.ksenia.ituproject.ui.listadapters.CurrencyRateAdapter;

public class CurrencyActivity extends AppCompatActivity {
    private CurrencyRateAdapter currencyRateAdapter;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list_currencies);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        currencyRateAdapter = new CurrencyRateAdapter();
        currencyRateAdapter.insert();
        recyclerView.setAdapter(currencyRateAdapter);

    }


}

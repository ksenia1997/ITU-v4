package com.example.ksenia.ituproject.ui.listadapters;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ksenia.ituproject.MyApp;
import com.example.ksenia.ituproject.R;
import com.example.ksenia.ituproject.model.Category;
import com.example.ksenia.ituproject.model.Currency;
import com.example.ksenia.ituproject.model.Status;
import com.example.ksenia.ituproject.ui.activities.CurrencyActivity;

import java.util.ArrayList;
import java.util.List;


public class CurrencyRateAdapter extends RecyclerView.Adapter {

    private ArrayList<Currency> data_currency = new ArrayList();
    EditText currencyValue;
    String strCurrencyValue;
  
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // get LayoutInflater
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        // inflate View from layout file
        View view = inflater.inflate(R.layout.item_row, viewGroup, false);
        // create view holder
        CurrencyRateViewHolder viewHolder = new CurrencyRateViewHolder(view);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        // cast
        CurrencyRateViewHolder vh = (CurrencyRateViewHolder) viewHolder;

        // find current item
        Currency dataItem = data_currency.get(i);
        // display values
        vh.txtTitle.setText(dataItem.getName());

        vh.root.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showListCurrency(v);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data_currency.size();
    }

    public void insert() {
        data_currency.addAll(Status.getCurrencies());
        notifyDataSetChanged();

    }

    public class CurrencyRateViewHolder extends RecyclerView.ViewHolder{
        public View root;
        public TextView txtTitle;

        public CurrencyRateViewHolder(@NonNull View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.item_row_root);
            txtTitle = itemView.findViewById(R.id.item_row);
        }
    }

       public void showListCurrency (View v) {

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(v.getRootView().getContext());
        // Nastavovani titulku.
        mBuilder.setTitle("Set currency race");

        //
        currencyValue = new EditText(v.getRootView().getContext());
        mBuilder.setView(currencyValue);

        mBuilder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialog, int which) {
                    strCurrencyValue = currencyValue.getText().toString();

                    if (! isNumeric(strCurrencyValue)) {
                        strCurrencyValue = "";
                    }


               }
        });
        //negative button
        mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        // Zobrazeni dialogoveho okna.
        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }

    public static boolean isNumeric(String str)
    {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

}

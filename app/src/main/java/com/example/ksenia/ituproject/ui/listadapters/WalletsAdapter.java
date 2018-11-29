package com.example.ksenia.ituproject.ui.listadapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ksenia.ituproject.R;

public class WalletsAdapter extends RecyclerView.Adapter {
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        // inflate View from layout file
        View view = inflater.inflate(R.layout.item_wallet, viewGroup, false);
        // create view holder

        return new WalletsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class WalletsViewHolder extends RecyclerView.ViewHolder {
        View root;
        public View icon;
        TextView txtTitle;

        WalletsViewHolder(@NonNull View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.item_wallet_root);
            icon = itemView.findViewById(R.id.item_wallet_color);
            txtTitle = itemView.findViewById(R.id.item_wallet_title);
        }
    }

}

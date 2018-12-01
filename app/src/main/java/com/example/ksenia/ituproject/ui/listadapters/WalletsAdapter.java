package com.example.ksenia.ituproject.ui.listadapters;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ksenia.ituproject.MyApp;
import com.example.ksenia.ituproject.R;
import com.example.ksenia.ituproject.model.Wallet;
import com.example.ksenia.ituproject.ui.activities.MainActivity;
import com.example.ksenia.ituproject.ui.fragments.OperationsFragment;

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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        final WalletsViewHolder walletsViewHolder = (WalletsViewHolder) viewHolder;
        final Wallet wallet = MyApp.status.getWallets().get(i);
        walletsViewHolder.txtTitle.setText(wallet.getName() + " (" + wallet.getBalance() + ")");
        walletsViewHolder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("a", wallet.getName());
                ((FragmentActivity) v.getContext()).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, OperationsFragment.newInstance(walletsViewHolder.getAdapterPosition()))
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return MyApp.status.getWallets().size();
    }

    public class WalletsViewHolder extends RecyclerView.ViewHolder {
        View root;
        public View icon;
        TextView txtTitle;

        WalletsViewHolder(@NonNull View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.item_wallet_root);
            txtTitle = itemView.findViewById(R.id.item_wallet_title);
        }
    }

}

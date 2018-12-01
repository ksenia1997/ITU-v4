package com.example.ksenia.ituproject.ui.listadapters;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
        final WalletsViewHolder walletViewHolder = (WalletsViewHolder) viewHolder;
        final Wallet wallet = MyApp.status.getWallets().get(i);
        walletViewHolder.txtTitle.setText(wallet.getName());
        walletViewHolder.summaryTextView.setText(MyApp.status.getWalletSummary(wallet));
        walletViewHolder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("a", wallet.getName());
                ((FragmentActivity) v.getContext()).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, OperationsFragment.newInstance(walletViewHolder.getAdapterPosition()))
                        .commit();
            }
        });
        walletViewHolder.root.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Remove " + wallet.toString());
                TextView textView = new TextView(v.getContext());
                textView.setText("Are you sure you want to remove wallet \""+ wallet.toString() +"\""
                        + " and its operations ("+wallet.getOperations().size()+")?");
                textView.setPadding(30, 10, 30, 10);
                builder.setView(textView);
                builder.setPositiveButton("Remove", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MyApp.status.removeWallet(wallet);
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.create().show();

                return true;
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
        TextView summaryTextView;

        WalletsViewHolder(@NonNull View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.item_wallet_root);
            txtTitle = itemView.findViewById(R.id.item_wallet_title);
            summaryTextView = itemView.findViewById(R.id.item_wallet_summary);
        }
    }

}

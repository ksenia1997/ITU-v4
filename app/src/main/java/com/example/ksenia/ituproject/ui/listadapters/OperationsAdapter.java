package com.example.ksenia.ituproject.ui.listadapters;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ksenia.ituproject.MyApp;
import com.example.ksenia.ituproject.R;
import com.example.ksenia.ituproject.model.Operation;
import com.example.ksenia.ituproject.model.Wallet;

public class OperationsAdapter extends RecyclerView.Adapter {

    private final Wallet wallet;

    public OperationsAdapter(Wallet wallet) {
        super();
        this.wallet = wallet;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        // inflate View from layout file
        View view = inflater.inflate(R.layout.item_operation, viewGroup, false);
        // create view holder

        return new OperationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        OperationViewHolder operationViewHolder = (OperationViewHolder) viewHolder;
        final Operation operation = wallet.getNthOperationFromEnd(i);  // descending
        operationViewHolder.amountTextView.setText(Float.toString(operation.getAmount()));
        operationViewHolder.descriptionTextView.setText(operation.getDescription() != null ? operation.getDescription() : "");
        operationViewHolder.categoryTextView.setText(
                operation.getCategory() != null ? operation.getCategory().getTitle() : ""
        );

        operationViewHolder.root.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                String type = operation.isIncome() ? "income" : "expense";
                builder.setTitle("Remove " + type);
                TextView textView = new TextView(v.getContext());
                textView.setText("Are you sure you want to remove this " + type + "?\n\nAmount: "
                        +operation.getAmount()+"\nCurrency: " + operation.getCurrency().toString());
                textView.setPadding(30, 10, 30, 10);
                builder.setView(textView);
                builder.setPositiveButton("Remove", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        operation.remove();
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

        /*final WalletsViewHolder walletsViewHolder = (WalletsViewHolder) viewHolder;
        final Wallet wallet = MainActivity.status.getWallets().get(i);
        walletsViewHolder.txtTitle.setText(wallet.getName() + " (" + wallet.getBalance() + ")");
        walletsViewHolder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("a", wallet.getName());
                ((FragmentActivity) v.getContext()).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, OperationsFragment.newInstance(walletsViewHolder.getAdapterPosition()))
                        .commit();
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return wallet.getOperations().size();
    }

    public class OperationViewHolder extends RecyclerView.ViewHolder {
        View root;
        TextView amountTextView;
        TextView categoryTextView;
        TextView descriptionTextView;

        OperationViewHolder(@NonNull View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.operationItemRoot);
            amountTextView = itemView.findViewById(R.id.operationAmountTextView);
            categoryTextView = itemView.findViewById(R.id.operationCategoryTextView);
            descriptionTextView = itemView.findViewById(R.id.operationDescriptionTextView);
        }
    }

}

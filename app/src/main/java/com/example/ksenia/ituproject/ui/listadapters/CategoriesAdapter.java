package com.example.ksenia.ituproject.ui.listadapters;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ksenia.ituproject.R;
import com.example.ksenia.ituproject.model.Category;

import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter {

    private ArrayList<Category> data = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        // get LayoutInflater
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        // inflate View from layout file
        View view = inflater.inflate(R.layout.item_category, viewGroup, false);
        // create view holder
        CategoryViewHolder viewHolder = new CategoryViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        // cast
        CategoryViewHolder vh = (CategoryViewHolder) viewHolder;

        // find current item
        Category currentItem = data.get(i);

        // display values
        vh.txtTitle.setText(currentItem.getTitle());
        vh.icon.setBackgroundColor(currentItem.getColour());

        vh.root.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                remove(i);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void loadData() {
        data.add(new Category("Food", Color.CYAN));
        data.add(new Category("Smoking", Color.YELLOW));
        data.add(new Category("Health", Color.GREEN));
        data.add(new Category("Entertainment", Color.RED));
    }

    public void insert(Category newCategory) {
        data.add(newCategory);
        notifyItemInserted(data.size() - 1);
    }

    public void remove(int position) {
        data.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, data.size()-1);
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{
        public View root;
        public View icon;
        public TextView txtTitle;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.item_category_root);
            icon = itemView.findViewById(R.id.item_category_color);
            txtTitle = itemView.findViewById(R.id.item_category_title);
        }
    }
}

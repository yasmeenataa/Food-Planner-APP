package com.example.foodplannerapp.ui.detailsFragment.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplannerapp.R;

import java.util.ArrayList;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.Holder> {

    private ArrayList<String> ingredientList;

    private ArrayList<String> measurementList;

    public void setIngredientList(ArrayList<String> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public void setMeasurementList(ArrayList<String> measurementList) {
        this.measurementList = measurementList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_details_ingredients, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.textView_ingredientName.setText(ingredientList.get(position));
        holder.textView_ingredientMeasure.setText(measurementList.get(position));
        Glide.with(holder.itemView.getContext())
                .load(String.format("https://www.themealdb.com/images/ingredients/%s-Small.png", ingredientList.get(position)))
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.imageFood);

    }

    @Override
    public int getItemCount() {
        return ingredientList == null ? 0 : ingredientList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        ImageView imageFood;
        TextView textView_ingredientName, textView_ingredientMeasure;

        public Holder(@NonNull View itemView) {
            super(itemView);
            imageFood = itemView.findViewById(R.id.image_ingredient);
            textView_ingredientMeasure = itemView.findViewById(R.id.text_view_ingredient_measure);
            textView_ingredientName = itemView.findViewById(R.id.text_view_ingredient_name);
        }
    }
}

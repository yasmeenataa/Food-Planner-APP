package com.example.foodplannerapp.ui.detailsFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplannerapp.R;
import com.example.foodplannerapp.models.MealModel;

import java.util.ArrayList;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.Holder> {

    private ArrayList<MealModel> list;

    public void setList(ArrayList<MealModel> list) {
        this.list = list;
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
        MealModel mealModel = list.get(position);
        holder.textView_ingredientName.setText(mealModel.getStrIngredient1());
        holder.textView_ingredientMeasure.setText(mealModel.getStrMeasure1());

        // glide

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        ImageView imageFood;
        TextView textView_ingredientName, textView_ingredientMeasure;

        public Holder(@NonNull View itemView) {
            super(itemView);
            imageFood = itemView.findViewById(R.id.image_meal);
            textView_ingredientMeasure = itemView.findViewById(R.id.text_view_ingredient_measure);
            textView_ingredientName = itemView.findViewById(R.id.text_view_ingredient_name);
        }
    }
}

package com.example.foodplannerapp.ui.allCategory.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplannerapp.R;
import com.example.foodplannerapp.models.ModelMeal;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

public class AdapterAllMealsFromCat extends RecyclerView.Adapter<AdapterAllMealsFromCat.Holder> {

    private ArrayList<ModelMeal> list;
    private SetOnMealClickListener onMealClickListener;

    public void setOnMealClickListener(SetOnMealClickListener onMealClickListener) {
        this.onMealClickListener = onMealClickListener;
    }

    public void setList(ArrayList<ModelMeal> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_meal, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        ModelMeal modelMeal = list.get(position);
        Log.i("TAG", "name : " + modelMeal.getStrMealThumb());
        holder.textViewMealName.setText(modelMeal.getStrMeal());
        Glide.with(holder.itemView.getContext())
                .load(modelMeal.getStrMealThumb())
                .into(holder.imageViewMealThumb);


    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        ImageView imageViewMealThumb;
        TextView textViewMealName;

        public Holder(@NonNull View itemView) {
            super(itemView);
            imageViewMealThumb = itemView.findViewById(R.id.image_meal_itemMeal);
            textViewMealName = itemView.findViewById(R.id.text_view_maelName_itemMeal);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onMealClickListener != null) {
                        onMealClickListener.onMealClick(list.get(getLayoutPosition()).getIdMeal());
                    }
                }
            });
        }
    }

    interface SetOnMealClickListener {
        void onMealClick(String mealId);

        void onFavClick(ModelMeal modelMeal);
    }
}

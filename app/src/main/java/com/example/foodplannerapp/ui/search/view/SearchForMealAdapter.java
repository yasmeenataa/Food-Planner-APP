package com.example.foodplannerapp.ui.search.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplannerapp.R;
import com.example.foodplannerapp.models.IngredientListModel;
import com.example.foodplannerapp.models.ModelMeal;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchForMealAdapter extends RecyclerView.Adapter<SearchForMealAdapter.Holder> {

    private List<ModelMeal> values;
    private Context context;
    private OnItemClickListener listener;


    public SearchForMealAdapter(List<ModelMeal> values, Context context, OnItemClickListener listener) {
        this.values = values;
        this.context = context;
        this.listener = listener;
    }

    public void setList(List<ModelMeal> list) {
        this.values = list;
    }

    @NonNull
    @Override
    public SearchForMealAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ingredients_row, parent, false);
        SearchForMealAdapter.Holder holder = new SearchForMealAdapter.Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchForMealAdapter.Holder holder, int position) {
        ModelMeal mealModel = values.get(position);
        holder.ingredient_name.setText(mealModel.getStrMeal());
        Glide.with(holder.itemView.getContext())
                .load(mealModel.getStrMealThumb())
                .apply(new RequestOptions())
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_background)
                .into(holder.ingredient_img);

    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        CircleImageView ingredient_img;
        TextView ingredient_name;


        public Holder(@NonNull View itemView) {
            super(itemView);

            ingredient_img = itemView.findViewById(R.id.ingredient_img);
            ingredient_name = itemView.findViewById(R.id.ingredient_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onSearchedMealClick(values.get(getLayoutPosition()).getIdMeal());
                }
            });


        }
    }
}

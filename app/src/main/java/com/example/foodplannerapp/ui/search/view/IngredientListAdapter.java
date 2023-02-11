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
import com.example.foodplannerapp.models.CategoriesModel;
import com.example.foodplannerapp.models.IngredientListModel;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class IngredientListAdapter extends RecyclerView.Adapter<IngredientListAdapter.Holder> {

    private List<IngredientListModel> values;
    private Context context;
    private OnItemClickListener listener;



    public IngredientListAdapter(List<IngredientListModel> values, Context context,OnItemClickListener listener) {
        this.values = values;
        this.context = context;
        this.listener=listener;
    }
    public void setList(ArrayList<IngredientListModel> list) {
        this.values = list;
    }

    @NonNull
    @Override
    public IngredientListAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ingredients_row, parent, false);
        IngredientListAdapter.Holder holder = new IngredientListAdapter.Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientListAdapter.Holder holder, int position) {
        IngredientListModel mealModel = values.get(position);
        holder.ingredient_name.setText(mealModel.getStrIngredient());
        String image=String.format("https://www.themealdb.com/images/ingredients/%s.png", mealModel.getStrIngredient());
        Glide.with(context).load(image).apply(new RequestOptions()
                        .override(80,80))
                .placeholder(R.drawable.ic_launcher_foreground).error(R.drawable.ic_launcher_background).into(holder.ingredient_img);
        holder.ingredient_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnIngredientClick();
            }
        });
        holder.ingredient_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnIngredientClick();
            }
        });

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


        }
    }
}


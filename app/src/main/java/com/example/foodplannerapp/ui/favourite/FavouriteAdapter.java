package com.example.foodplannerapp.ui.favourite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplannerapp.R;
import com.example.foodplannerapp.models.ModelMeal;

import java.util.ArrayList;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.Holder> {

    private ArrayList<ModelMeal> list;

    private setOnItemListener onItemListener;

    public void setOnItemListener(setOnItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }

    public void setList(ArrayList<ModelMeal> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_favourite_list, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        ModelMeal mealModel = list.get(position);
        holder.textView_mealName.setText(mealModel.getStrMeal());
        holder.textView_category.setText(mealModel.getStrCategory());
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        ImageView btnDelete, imageMeal;
        TextView textView_mealName, textView_category;


        public Holder(@NonNull View itemView) {
            super(itemView);

            btnDelete = itemView.findViewById(R.id.btn_delete);
            imageMeal = itemView.findViewById(R.id.image_meal);
            textView_category = itemView.findViewById(R.id.text_view_category);
            textView_mealName = itemView.findViewById(R.id.text_view_mealName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemListener != null) {
                        onItemListener.onItemClick(list.get(getLayoutPosition()).getIdMeal());
                    }
                }
            });

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemListener != null) {
                        onItemListener.onItemDelete(list.get(getLayoutPosition()));
                    }
                }
            });
        }
    }

    public interface setOnItemListener {
        void onItemClick(String id);

        void onItemDelete(ModelMeal mealModel);
    }
}

package com.example.foodplannerapp.ui.weekPlanner.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplannerapp.R;
import com.example.foodplannerapp.models.WeekPlannerModel;

import java.util.ArrayList;

public class AdapterMealOfTheDay extends RecyclerView.Adapter<AdapterMealOfTheDay.Holder> {

    private ArrayList<WeekPlannerModel> list;

    private SetOnItemClickListener onItemClickListener;




    public void setOnItemClickListener(SetOnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setList(ArrayList<WeekPlannerModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_week_planner_recycler, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        WeekPlannerModel model = list.get(position);
        holder.textViewMealName.setText(model.getStrMeal());
        Glide.with(holder.itemView.getContext())
                .load(model.getStrMealThumb())
                .into(holder.mealImageWeekPlanner);

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        ImageView deleteImage, mealImageWeekPlanner;
        TextView textViewMealName;

        public Holder(@NonNull View itemView) {
            super(itemView);
            deleteImage = itemView.findViewById(R.id.imageDeleteWeekItem);
            textViewMealName = itemView.findViewById(R.id.text_view_meal_name_inWeekFrag);
            mealImageWeekPlanner = itemView.findViewById(R.id.image_of_weekPlanner);

            deleteImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onDeleteClicked(list.get(getLayoutPosition()));
                    }

                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClicked(list.get(getLayoutPosition()));
                    }
                }
            });


        }
    }

    interface SetOnItemClickListener {
        void onDeleteClicked(WeekPlannerModel model);

        void onItemClicked(WeekPlannerModel weekPlannerModel);
    }
}

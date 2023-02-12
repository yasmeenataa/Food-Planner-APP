package com.example.foodplannerapp.ui.resulFromSearch.view;

import android.content.Context;
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

public class ResultSearchAdapter extends RecyclerView.Adapter<ResultSearchAdapter.Holder>{

    private ArrayList<ModelMeal> list;
    private Context context;
    private OnResultClickListener listener;

    public ResultSearchAdapter(ArrayList<ModelMeal> list, Context context,OnResultClickListener listener) {
        this.list = list;
        this.context = context;
        this.listener=listener;
    }
    public void setList(ArrayList<ModelMeal> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ResultSearchAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.result_search_row, parent, false);
        ResultSearchAdapter.Holder holder = new ResultSearchAdapter.Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ResultSearchAdapter.Holder holder, int position) {
        ModelMeal modelMeal=list.get(position);
        holder.txtResult_maelName_itemMeal.setText(modelMeal.getStrMeal());
        Glide.with(holder.itemView.getContext())
                .load(modelMeal.getStrMealThumb())
                .into(holder.image_result_itemMeal);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class Holder extends RecyclerView.ViewHolder {
        TextView txtResult_maelName_itemMeal;
        ImageView image_result_itemMeal;


        public Holder(@NonNull View itemView) {
            super(itemView);

            txtResult_maelName_itemMeal = itemView.findViewById(R.id.txtResult_maelName_itemMeal);
            image_result_itemMeal=itemView.findViewById(R.id.image_result_itemMeal);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onMealClick(list.get(getLayoutPosition()).getIdMeal());
                }
            });
        }
    }
}

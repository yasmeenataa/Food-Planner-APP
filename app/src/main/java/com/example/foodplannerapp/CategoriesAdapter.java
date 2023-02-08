package com.example.foodplannerapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplannerapp.databinding.FragmentFavouriteBinding;
import com.example.foodplannerapp.databinding.FragmentSearchBinding;
import com.example.foodplannerapp.models.CategoriesModel;
import com.example.foodplannerapp.models.MealModel;
import com.example.foodplannerapp.ui.favourite.FavouriteAdapter;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.Holder> {

    private List<CategoriesModel> values;
    private Context context;

    public CategoriesAdapter(List<CategoriesModel> values, Context context) {
        this.values = values;
        this.context = context;
    }
    public void setList(ArrayList<CategoriesModel> list) {
        this.values = list;
    }

    @NonNull
    @Override
    public CategoriesAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_row, parent, false);
        CategoriesAdapter.Holder holder = new CategoriesAdapter.Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesAdapter.Holder holder, int position) {
        CategoriesModel mealModel = values.get(position);
        holder.category_name.setText(mealModel.getStrCategory());
        holder.category_image.setImageResource(R.drawable.beef);
//        Glide.with(context).load(values.get(position).getStrCategoryThumb()).apply(new RequestOptions()
//                .override(100,100)).placeholder(R.drawable.ic_launcher_foreground).error(R.drawable.ic_launcher_background).into(holder.category_image);

    }

    @Override
    public int getItemCount() {
        return values.size();
    }
    public class Holder extends RecyclerView.ViewHolder {
        CircleImageView category_image;
        TextView category_name;


        public Holder(@NonNull View itemView) {
            super(itemView);

            category_image = itemView.findViewById(R.id.category_image);
            category_name = itemView.findViewById(R.id.category_name);


        }
    }
}


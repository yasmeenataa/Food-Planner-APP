
package com.example.foodplannerapp.ui.home.view;

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

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.Holder> {

    private List<CategoriesModel> list;

    private SetOnCategoryClickListener onCategoryClickListener;

    public void setOnCategoryClickListener(SetOnCategoryClickListener onCategoryClickListener) {
        this.onCategoryClickListener = onCategoryClickListener;
    }

    public void setList(ArrayList<CategoriesModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public CategoriesAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_row, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesAdapter.Holder holder, int position) {
        CategoriesModel categoriesModel = list.get(position);
        holder.category_name.setText(categoriesModel.getStrCategory());
        holder.category_image.setImageResource(R.drawable.beef);
        Glide.with(holder.itemView.getContext())
                .load(categoriesModel.getStrCategoryThumb())
                .apply(new RequestOptions()
                        .override(100, 100))
                .error(R.drawable.ic_launcher_background)
                .into(holder.category_image);

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        CircleImageView category_image;
        TextView category_name;


        public Holder(@NonNull View itemView) {
            super(itemView);

            category_image = itemView.findViewById(R.id.category_image);
            category_name = itemView.findViewById(R.id.category_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onCategoryClickListener != null) {
                        onCategoryClickListener.onCategoryClicked(list.get(getLayoutPosition()).getStrCategory());
                    }
                }
            });


        }
    }

    public interface SetOnCategoryClickListener {
        void onCategoryClicked(String categoryName);
    }
}


package com.example.foodplannerapp.ui.search.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplannerapp.R;
import com.example.foodplannerapp.models.AreaListModel;
import com.example.foodplannerapp.models.IngredientListModel;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AreaListAdapter extends RecyclerView.Adapter<AreaListAdapter.Holder>{


        private List<AreaListModel> values;
        private Context context;
        private OnItemClickListener listener;

        public AreaListAdapter(List<AreaListModel> values, Context context,OnItemClickListener listener) {
            this.values = values;
            this.context = context;
            this.listener=listener;
        }
        public void setList(ArrayList<AreaListModel> list) {
            this.values = list;
        }

        @NonNull
        @Override
        public AreaListAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.area_row, parent, false);
            AreaListAdapter.Holder holder = new AreaListAdapter.Holder(view);
            return holder;
        }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        AreaListModel areaModel=values.get(position);
        holder.area_name.setText(areaModel.getStrArea());
        holder.area_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnAreaClick(areaModel.getStrArea());
            }
        });

    }

        @Override
        public int getItemCount() {
            return values.size();
        }
        public class Holder extends RecyclerView.ViewHolder {
            TextView area_name;


            public Holder(@NonNull View itemView) {
                super(itemView);

                area_name = itemView.findViewById(R.id.area_name);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.OnAreaClick(values.get(getLayoutPosition()).getStrArea());
                    }
                });
            }
        }
}

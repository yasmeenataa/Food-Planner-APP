package com.example.foodplannerapp.ui.weekPlanner.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplannerapp.R;

import java.util.ArrayList;

public class AdapterDays extends RecyclerView.Adapter<AdapterDays.Holder> {

    private ArrayList<String> list;
    private SetOnDayClickListener onDayClickListener ;

    public void setOnDayClickListener(SetOnDayClickListener onDayClickListener) {
        this.onDayClickListener = onDayClickListener;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_days, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.dayTextView.setText(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView dayTextView;

        public Holder(@NonNull View itemView) {
            super(itemView);
            dayTextView = itemView.findViewById(R.id.text_view_Itemday);
            dayTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onDayClickListener !=null){
                        onDayClickListener.onItemClick(list.get(getLayoutPosition()));
                    }

                }
            });
        }
    }

    interface SetOnDayClickListener{
        void onItemClick(String day);
    }
}

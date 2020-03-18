package com.example.myapplication;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class recyclerAdapter extends RecyclerView.Adapter<recyclercontroller> {

    private List<doctordetailresult> list;



    public recyclerAdapter(List<doctordetailresult> list) {
        this.list = list;

    }

    @NonNull
    @Override
    public recyclercontroller onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);

        return new recyclercontroller(view);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclercontroller holder, int position) {
        doctordetailresult  doctordetailresult=list.get(position);
        holder.name.setText(doctordetailresult.getName());
        holder.spec.setText(doctordetailresult.getSpeciality());
        holder.intime.setText(doctordetailresult.getIntime());
        holder.outtime.setText(doctordetailresult.getOuttime());
        holder.id.setText(doctordetailresult.getDoctorid());
        holder.fees.setText(doctordetailresult.getFees());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

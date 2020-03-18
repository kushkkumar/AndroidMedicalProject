package com.example.myapplication;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class recyclercontroller extends RecyclerView.ViewHolder {

    TextView name,spec,id,intime,outtime,fees;

    public recyclercontroller(@NonNull View itemView) {
        super(itemView);
        name=itemView.findViewById(R.id.dctname);
        spec=itemView.findViewById(R.id.dctspc);
        id=itemView.findViewById(R.id.dctid);
        intime=itemView.findViewById(R.id.dctintime);
        outtime=itemView.findViewById(R.id.dctouttime);
        fees=itemView.findViewById(R.id.dctfees);
    }
}

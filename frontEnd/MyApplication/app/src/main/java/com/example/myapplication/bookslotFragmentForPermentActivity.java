package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class bookslotFragmentForPermentActivity extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.book_slot_fragment,container,false);

        view.findViewById(R.id.get_slot_number).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearStack();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame1,new qrcodePreviewFragmentActivity()).addToBackStack(null).commit();


            }
        });




        return view;
    }

    private void clearStack() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
    }
}

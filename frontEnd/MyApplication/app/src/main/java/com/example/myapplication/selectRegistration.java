package com.example.myapplication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

public class selectRegistration extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.resgistration_first_fragment,container,false);
        view.findViewById(R.id.permentReg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame1,new permanentRegistration()).addToBackStack(null).commit();
            }
        });
        view.findViewById(R.id.onetime).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame1,new onTimeActivity()).addToBackStack(null).commit();
            }
        });
        view.findViewById(R.id.permanent_book_slot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertfunction();



//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame1,new bookslotFragmentActivity()).addToBackStack(null).commit();

            }
        });
        return view;
    }

    private void alertfunction() {

        AlertDialog.Builder builder;
        builder=new AlertDialog.Builder(getActivity());
        builder.setTitle("Color?")
                .setMessage("Enter your patient ID");
        final EditText input = new EditText(getActivity());
        input.setInputType(InputType.TYPE_CLASS_TEXT );
        builder.setView(input)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if(input.getText().toString().equals("ankush")){
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame1,new bookslotFragmentActivity()).addToBackStack(null).commit();
                        }
                        else{
                            Toast.makeText(getContext(),"error you are not the permanent patient",Toast.LENGTH_SHORT).show();
                            alertfunction();
                        }



                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();


    }
}

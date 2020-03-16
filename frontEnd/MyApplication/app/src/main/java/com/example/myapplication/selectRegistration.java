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

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class selectRegistration extends Fragment {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL="https://young-sea-56344.herokuapp.com/";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.resgistration_first_fragment,container,false);


        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface=retrofit.create(RetrofitInterface.class);




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
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        HashMap<String,String> map2=new HashMap<>();

                        map2.put("ppid",input.getText().toString());

                        Call<permanentPateintResult> call=retrofitInterface.executebookSlotPermanent(map2);
                        call.enqueue(new Callback<permanentPateintResult>() {
                            @Override
                            public void onResponse(Call<permanentPateintResult> call, Response<permanentPateintResult> response) {
                                if(response.code()==202){
                                    permanentPateintResult permanentPateintResult=response.body();
                                    if(permanentPateintResult.getPpid().equals(setter.getPtid())){
                                        shiftActivity();
                                    }
                                    else {
                                        Toast.makeText(getContext(),"You are not the permanent patient or try giving proper patient id",Toast.LENGTH_SHORT).show();
                                        alertfunction();

                                    }



                                }
                                if(response.code()==404){
                                    Toast.makeText(getContext(),"You are not the permanent patient or try giving proper patient id",Toast.LENGTH_SHORT).show();
                                    alertfunction();

                                }

                            }

                            @Override
                            public void onFailure(Call<permanentPateintResult> call, Throwable t) {
                                t.printStackTrace();
                                Toast.makeText(getContext(),"Error"+t,Toast.LENGTH_LONG).show();
                                alertfunction();

                            }
                        });




                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();


    }

    private void shiftActivity() {

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame1,new bookslotFragmentActivity()).addToBackStack(null).commit();
    }
}
